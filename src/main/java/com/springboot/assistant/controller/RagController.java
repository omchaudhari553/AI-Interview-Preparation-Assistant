package com.springboot.assistant.controller;

import com.springboot.assistant.model.ChatRequest;
import com.springboot.assistant.service.RagService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/rag")
@RequiredArgsConstructor
public class RagController {

    private final RagService ragService;

    @PostMapping("/ask")
    public String ask(
            @RequestBody ChatRequest request) {

        return ragService.askQuestion(
                request.getQuestion());
    }
}
