package com.springboot.assistant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.SearchRequest;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RAGService {

    private final VectorStore vectorStore;

    private final ChatClient chatClient;

    public String askQuestion(String question) {

        List<Document> documents =
                vectorStore.similaritySearch(
                        SearchRequest.builder()
                                .query(question)
                                .topK(5)
                                .build()
                );

        StringBuilder context =
                new StringBuilder();

        documents.forEach(doc ->
                context.append(doc.getText())
                        .append("\n"));

        String prompt = """
                You are a Java interview assistant.

                Use ONLY the context below.

                Context:
                %s

                Question:
                %s
                """
                .formatted(context, question);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
