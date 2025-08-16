\documentclass{sigchi}

\usepackage{graphicx}

\begin{document}

\title{AI-Assisted Legacy Code Reverse Engineering for COBOL Migration}

\author{Debasish Nath}

\maketitle

\section{Introduction}
Government and enterprise institutions still heavily rely on legacy COBOL systems to manage critical, high-frequency operations. Due to a rapidly shrinking talent pool familiar with COBOL, institutions face severe operational risks, compliance issues, and competitive disadvantages. To address these challenges, we propose an innovative solution leveraging Generative AI, Retrieval-Augmented Generation (RAG), and agentic AI personas designed specifically to reverse-engineer legacy COBOL codebases, facilitating swift migration to modern programming languages.

\section{Literature Review}

Recent advances in AI and machine learning have significantly impacted legacy code migration. Generative AI models like GPT-4 and Claude 3.7 Sonnet have demonstrated robust capabilities in translating natural language descriptions into executable code, making them suitable for code comprehension and migration tasks \cite{openai2025gpt, anthropic2025claude}. Retrieval-Augmented Generation (RAG) systems enhance these capabilities by integrating context-specific retrieval, improving accuracy in legacy code translation by grounding output in real historical business rules and examples \cite{lewis2020retrieval}. Additionally, the application of Agentic AI personas that simulate domain-specific human expertise further reduces comprehension barriers for legacy systems \cite{smith2024agentic}.

Existing platforms, such as Heirloom and Micro Focus, offer COBOL migration solutions; however, these largely rely on traditional parsing methods and lack deep integration with modern AI capabilities \cite{heirloom2023migration, microfocus2023cobol}. Our approach uniquely combines generative modeling, RAG, and agentic AI, providing unprecedented accuracy, context-awareness, and adaptability for enterprise-specific migration scenarios.

\section{Market Research}

\subsection{Competitors and Platforms}

\begin{itemize}
\item \textbf{Heirloom}: Specializes in automatic code conversion but lacks robust AI-driven interpretability and context-awareness.
\item \textbf{Micro Focus Enterprise Analyzer}: Powerful static analysis tools, yet limited AI integration and dependency visualization.
\item \textbf{Raincode Labs}: Provides modern compilers and runtime solutions but doesn't incorporate sophisticated generative AI mechanisms.
\end{itemize}

\subsection{Customer Expectations and Pain Points}
Customers expect solutions that mitigate the risks associated with legacy COBOL, especially as expertise diminishes. Common pain points include:
\begin{itemize}
\item High costs and prolonged timelines of manual migration.
\item Difficulty in understanding system dependencies and business logic embedded in legacy systems.
\item Regulatory and compliance risks arising from poorly documented legacy code.
\end{itemize}

\subsection{Pricing, Business Models, and Adoption Barriers}
Competitor pricing often follows licensing and subscription models, typically with high initial investments. Adoption barriers include lack of trust in automated migration, concerns about accuracy, and cultural resistance within institutions. Our model proposes competitive subscription pricing and consultancy-based business models, emphasizing a human-in-the-loop approach for accuracy and trust.

\section{Proposed Solution and Distinctive Value}

Our AI-driven platform provides:

\begin{enumerate}
\item \textbf{Semi-automated reverse engineering (human-in-the-loop)}: Ensures trust and accuracy in migrations.
\item \textbf{MVP focused on single subsystem}: Clear, measurable benefits and simplified validation processes.
\item \textbf{Integrated visual dependency graphs}: Improves stakeholder comprehension and decision-making.
\item \textbf{Fine-tuned RAG pipelines}: Ensures output closely aligns with actual business rules and historical logic.
\item \textbf{Customizable solutions}: Tailored specifically for governmental and enterprise internal datasets and knowledge.
\end{enumerate}

\section{Conclusion}
The proposed AI-assisted reverse engineering platform represents a substantial innovation over current market solutions, directly addressing critical challenges institutions face with COBOL modernization. Through targeted MVPs, intelligent generative AI systems, and interactive human-AI collaboration, our solution offers distinctive value and a realistic pathway to modern, maintainable, and scalable enterprise applications.

\bibliographystyle{ACM-Reference-Format}
\bibliography{references}

\end{document}
