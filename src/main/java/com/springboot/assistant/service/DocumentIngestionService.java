package com.springboot.assistant.service;

import lombok.RequiredArgsConstructor;
import org.springframework.ai.document.Document;
import org.springframework.ai.vectorstore.VectorStore;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentIngestionService {

    private final VectorStore vectorStore;

    public void ingest(String text) {

        String[] chunks = text.split("\\.");

        List<Document> docs =
                Arrays.stream(chunks)
                        .map(Document::new)
                        .toList();

        vectorStore.add(docs);
    }
}
