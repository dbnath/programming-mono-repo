"""Entry point for COBOL parsing demo.

Uses modular utilities in `agentic_rag.source.cobol_loader` to locate and
process COBOL source files instead of hard‑coding a specific path.
"""
from pathlib import Path

from agentic_rag.ingestion.source_intake import (
    load_first_cobol,
    build_cobol_segmenter,
    extract_segments,
    is_segmenter_valid,
    CobolFileNotFoundError,
)

def main() -> int:
    source_dir = Path("agentic_rag/source")
    try:
        cobol_path, cobol_code = load_first_cobol(source_dir)
    except CobolFileNotFoundError as e:
        print(f"[ERROR] {e}")
        return 1

    segmenter = build_cobol_segmenter(cobol_code)
    try:
        segments = extract_segments(segmenter)
    except AttributeError as e:
        print(f"[ERROR] Failed to extract segments: {e}")
        return 2

    valid = is_segmenter_valid(segmenter)
    print(f"File: {cobol_path}")
    print(f"Valid COBOL: {valid}")
    print(f"Segment count: {len(segments)}")
    for i, segment in enumerate(segments):
        print(f"--- Segment {i} ---")
        print(segment)
    return 0 if valid else 3


if __name__ == "__main__":  # pragma: no cover
    raise SystemExit(main())
