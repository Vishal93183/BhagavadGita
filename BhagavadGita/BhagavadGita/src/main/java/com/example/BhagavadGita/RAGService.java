package com.example.BhagavadGita;

import org.springframework.ai.ollama.OllamaChatModel;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class RAGService {

    private final OllamaChatModel chatModel;
    private final PdfReaderService pdfReaderService;

    public RAGService(OllamaChatModel chatModel, PdfReaderService pdfReaderService) {
        this.chatModel = chatModel;
        this.pdfReaderService = pdfReaderService;
    }

    public String generateAnswer(String userMessage) {
        // Load Gita text from PDF
        List<org.springframework.ai.document.Document> docs = pdfReaderService.getDocsFromPdf();

        // Combine all text into one context string
        String context = docs.stream()
                .map(org.springframework.ai.document.Document::getText)
                .collect(Collectors.joining("\n"));

        // Build final prompt
        String prompt = "You are a helpful assistant specializing in the Bhagavad Gita. "
                + "Use only the context provided to answer.\n\n"
                + "Context:\n" + context + "\n\n"
                + "User: " + userMessage;

        // Call Ollama model
        return chatModel.call(prompt);
    }
}
