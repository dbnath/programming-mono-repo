"""Utilities for discovering, reading, and segmenting COBOL source files.

This module centralizes COBOL file handling so that __main__ (and any future
code) does not need to hard‑code paths or parsing details.

Functions provided:
  find_cobol_files(base_dir, pattern) -> list[Path]
  load_first_cobol(base_dir, pattern) -> tuple[Path, str]
  read_cobol_file(path) -> str
  build_cobol_segmenter(code) -> CobolSegmenter instance
  extract_segments(segmenter) -> list[str]

All functions raise informative exceptions rather than exiting, allowing
callers to decide on error handling / logging strategy.
"""
from __future__ import annotations

from dataclasses import dataclass
from pathlib import Path
from typing import Iterable, List, Sequence, Tuple
from langchain_community.document_loaders.parsers.language.cobol import CobolSegmenter

DEFAULT_PATTERN = "*.cbl"

class CobolFileNotFoundError(FileNotFoundError):
    """Raised when no COBOL files are discovered in the given directory."""


def find_cobol_files(base_dir: str | Path, pattern: str = DEFAULT_PATTERN) -> List[Path]:
    """Return a list of COBOL source file Paths under base_dir matching pattern.

    Searches non‑recursively first; if nothing is found, attempts a recursive
    glob. This provides a convenient default while still locating nested files.
    """
    base = Path(base_dir).expanduser().resolve()
    if not base.exists():
        raise FileNotFoundError(f"Base directory does not exist: {base}")

    # First try non-recursive (common case)
    files = sorted(base.glob(pattern))
    if not files:
        # Fallback to recursive search
        files = sorted(base.rglob(pattern))
    return files


def read_cobol_file(path: str | Path, encoding: str = "utf-8") -> str:
    p = Path(path)
    if not p.is_file():
        raise FileNotFoundError(f"COBOL file not found: {p}")
    try:
        return p.read_text(encoding=encoding)
    except UnicodeDecodeError:
        # Retry with latin1 as a common fallback for legacy encodings
        return p.read_text(encoding="latin1")


def load_first_cobol(base_dir: str | Path, pattern: str = DEFAULT_PATTERN) -> Tuple[Path, str]:
    files = find_cobol_files(base_dir, pattern)
    if not files:
        raise CobolFileNotFoundError(
            f"No COBOL files found in '{base_dir}' (pattern '{pattern}')."
        )
    first = files[0]
    return first, read_cobol_file(first)


def build_cobol_segmenter(code: str):
    """Instantiate and return a CobolSegmenter for the given source code.

    Import is local to keep import time & optional dependency surface minimal.
    """

    return CobolSegmenter(code)


def extract_segments(segmenter) -> List[str]:
    """Extract segments from a CobolSegmenter, being tolerant of API changes.

    Newer / older versions may rename the method; we try a few candidates.
    """
    candidate_methods = [
        "extract_functions_classes",  # observed in current code
        "extract_functions_and_classes",  # possible variant
        "extract_segments",  # hypothetical fallback
    ]
    for name in candidate_methods:
        if hasattr(segmenter, name):
            method = getattr(segmenter, name)
            return list(method())
    raise AttributeError(
        "None of the expected extraction methods found on segmenter: "
        + ", ".join(candidate_methods)
    )


def is_segmenter_valid(segmenter) -> bool:
    return bool(hasattr(segmenter, "is_valid") and segmenter.is_valid())


__all__ = [
    "find_cobol_files",
    "read_cobol_file",
    "load_first_cobol",
    "build_cobol_segmenter",
    "extract_segments",
    "is_segmenter_valid",
    "CobolFileNotFoundError",
]
