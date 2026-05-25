package com.springboot.assistant.service;

import com.springboot.assistant.model.DocumentChunk;
import com.springboot.assistant.repository.DocumentChunkRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DocumentService {

    private final DocumentChunkRepository repository;

    public void upload(String text) {

        List<String> chunks =
                splitIntoChunks(text);

        chunks.forEach(chunk -> {

            repository.save(
                DocumentChunk.builder()
                    .chunkText(chunk)
                    .build()
            );

        });
    }

    private List<String> splitIntoChunks(
            String text) {

        return Arrays.asList(
                text.split("\\.")
        );
    }
}
