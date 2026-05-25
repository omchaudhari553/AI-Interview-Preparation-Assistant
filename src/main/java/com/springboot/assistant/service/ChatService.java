package com.springboot.assistant.service;

import com.springboot.assistant.model.ChatHistory;
import com.springboot.assistant.repository.ChatHistoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatService {

    private final ChatClient chatClient;

    private final ChatHistoryRepository historyRepository;

    public String ask(String question) {

        String answer = chatClient.prompt()
                .user(question)
                .call()
                .content();

        historyRepository.save(
            ChatHistory.builder()
                    .question(question)
                    .answer(answer)
                    .build()
        );

        return answer;
    }
}
