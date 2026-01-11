from setuptools import setup, find_packages

setup(
    name="langchain_model_config",
    version="0.1.0",
    packages=find_packages(),
    install_requires=[
        "python-dotenv",
        "langchain-openai",
    ],
    python_requires=">=3.12",
    description="Global LangChain Model configuration module",
    author="Debasish Nath",
)
