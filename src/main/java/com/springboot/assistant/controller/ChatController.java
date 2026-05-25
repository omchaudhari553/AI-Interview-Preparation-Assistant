package com.springboot.assistant.controller;

import com.springboot.assistant.model.ChatRequest;
import com.springboot.assistant.service.ChatService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ai")
@RequiredArgsConstructor
public class ChatController {

    private final ChatService chatService;

    @PostMapping("/ask")
    public String ask(
            @RequestBody ChatRequest request) {

        return chatService.ask(
                request.getQuestion());
    }
}
