Here's a complete, structured implementation with practical code snippets for creating a RAG-based pipeline to reverse-engineer COBOL code into Java, including semantic retrieval, generation, and human-in-the-loop feedback.

---

## ✅ **Full Implementation of RAG Pipeline for COBOL-to-Java Conversion**

### **Architecture Overview**

```
[COBOL Codebase] → [Semantic Embedding] → [Vector DB] → [Retrieval] → [LLM Generation] → [Java Output] → [Human Validation]
```

### **Implementation Steps**

---

## ⚙️ **1. Semantic Embeddings of COBOL Code**

Install dependencies:

```bash
pip install openai langchain pinecone-client python-dotenv
```

---

### **Step 1: Semantic Embedding of COBOL Code**

**File:** `embed_cobol.py`

```python
import os
from langchain.text_splitter import RecursiveCharacterTextSplitter
from langchain.embeddings import OpenAIEmbeddings
from pinecone import Pinecone
from dotenv import load_dotenv

load_dotenv()

openai_api_key = os.getenv("OPENAI_API_KEY")
pinecone_api_key = os.getenv("PINECONE_API_KEY")

# Initialize Pinecone
pc = Pinecone(api_key=pinecone_api_key)
index = pc.Index("cobol-code")

# Embedding Model
embeddings = OpenAIEmbeddings(openai_api_key=openai_api_key, model="text-embedding-3-small")

# Load COBOL code from directory
def load_cobol_files(directory):
    cobol_data = []
    for filename in os.listdir(directory):
        if filename.endswith(".cbl"):
            with open(os.path.join(directory, filename), 'r') as file:
                cobol_data.append(file.read())
    return cobol_data

# Embed and store COBOL code
def embed_and_store_cobol(directory):
    cobol_codes = load_cobol_files(directory)
    splitter = RecursiveCharacterTextSplitter(chunk_size=500, chunk_overlap=100)
    
    for code in cobol_codes:
        chunks = splitter.split_text(code)
        for chunk in chunks:
            embedding = embeddings.embed_query(chunk)
            index.upsert(vectors=[{"id": hash(chunk), "values": embedding, "metadata": {"code": chunk}}])

# Execute
if __name__ == "__main__":
    embed_and_store_cobol("path/to/cobol_codebase")
```

---

## ⚙️ **2. Retrieval of COBOL Context**

**File:** `retrieve_cobol.py`

```python
def retrieve_relevant_cobol(query, top_k=3):
    query_embedding = embeddings.embed_query(query)
    results = index.query(
        vector=query_embedding,
        top_k=top_k,
        include_metadata=True
    )
    return [match['metadata']['code'] for match in results['matches']]

# Example retrieval
if __name__ == "__main__":
    query = "Calculate loan interest rate"
    relevant_codes = retrieve_relevant_cobol(query)
    for code in relevant_codes:
        print(code)
```

---

## ⚙️ **3. Generation with RAG Using OpenAI GPT-4o**

**File:** `generate_java.py`

```python
from openai import OpenAI

client = OpenAI(api_key=openai_api_key)

def generate_java(cobol_snippets, instruction):
    prompt = f"""
    You are a highly experienced Java developer with expertise in converting COBOL code to Java. 
    Generate equivalent Java code maintaining business logic and providing clear documentation.

    COBOL CODE SNIPPETS:
    {cobol_snippets}

    INSTRUCTION:
    {instruction}

    JAVA OUTPUT:
    """
    response = client.chat.completions.create(
        model="gpt-4o",
        messages=[{"role": "user", "content": prompt}],
        temperature=0.2,
        max_tokens=1500,
    )
    return response.choices[0].message.content.strip()

# Example usage
if __name__ == "__main__":
    snippets = retrieve_relevant_cobol("Calculate loan interest rate")
    instruction = "Convert COBOL logic to Java method for calculating loan interest rate."
    java_code = generate_java(snippets, instruction)
    print(java_code)
```

---

## ⚙️ **4. Human-in-the-Loop Feedback (HITL)**

**Simple HITL Web Interface (Streamlit):**

```bash
pip install streamlit
```

**File:** `hitl_review.py`

```python
import streamlit as st
from generate_java import generate_java, retrieve_relevant_cobol

st.title("Human-in-the-loop COBOL to Java Validation")

user_query = st.text_input("Enter your COBOL conversion request:")
instruction = st.text_area("Additional instructions:")

if st.button("Generate Java"):
    snippets = retrieve_relevant_cobol(user_query)
    java_code = generate_java(snippets, instruction)
    st.code(java_code, language='java')

    feedback = st.text_area("Enter your feedback:")
    if st.button("Submit Feedback"):
        with open("feedback.txt", "a") as file:
            file.write(f"Query: {user_query}\nInstruction: {instruction}\nFeedback: {feedback}\n\n")
        st.success("Feedback submitted successfully!")
```

Run with:

```bash
streamlit run hitl_review.py
```

---

## ⚙️ **5. Iterative Embedding Improvement with Feedback**

Regularly use collected feedback to retrain embeddings or improve prompts.
A simplistic retraining logic (for demonstration):

**File:** `retrain_embeddings.py`

```python
def retrain_embeddings_from_feedback(feedback_file="feedback.txt"):
    with open(feedback_file, "r") as file:
        feedback_entries = file.read().split("\n\n")

    for entry in feedback_entries:
        if entry.strip() == "":
            continue
        query, instruction, feedback = [line.split(":", 1)[1].strip() for line in entry.strip().split("\n")]
        corrected_code = f"{instruction}\n// Feedback Correction:\n{feedback}"
        embedding = embeddings.embed_query(corrected_code)
        index.upsert(vectors=[{"id": hash(corrected_code), "values": embedding, "metadata": {"code": corrected_code}}])

if __name__ == "__main__":
    retrain_embeddings_from_feedback()
```

---

## ⚙️ **Project Structure**

```
├── .env
├── embed_cobol.py
├── retrieve_cobol.py
├── generate_java.py
├── hitl_review.py
├── retrain_embeddings.py
└── feedback.txt
```

**`.env` Configuration**

```
OPENAI_API_KEY='your_openai_api_key'
PINECONE_API_KEY='your_pinecone_api_key'
```

---

## 🚀 **Execution Steps:**

1. **Embed COBOL codebase:**

```bash
python embed_cobol.py
```

2. **Launch HITL Interface:**

```bash
streamlit run hitl_review.py
```

3. **Regularly retrain embeddings:**

```bash
python retrain_embeddings.py
```

---

### 🔐 **Security & Compliance Notes**

* Store all sensitive COBOL and generated Java code securely in company-approved infrastructure.
* Ensure compliance with data privacy, especially for industries like finance or healthcare.

---

## ✅ **Summary of Benefits:**

* Precise semantic retrieval ensures accuracy.
* RAG pipeline delivers context-rich Java translations.
* HITL integration maintains business logic correctness and continuous improvement.

Feel free to request more customization or further technical integration support.
