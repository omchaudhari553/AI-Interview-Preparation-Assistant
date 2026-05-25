package com.springboot.assistant.service;

import com.springboot.assistant.model.InterviewSession;
import com.springboot.assistant.repository.InterviewSessionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MockInterviewService {

    private final ChatClient chatClient;

    private final InterviewSessionRepository repository;

    public String startInterview() {

        return """
                Explain the difference between
                HashMap and ConcurrentHashMap.
                """;
    }

    public String evaluateAnswer(
            String answer) {

        String prompt = """
                You are a senior Java interviewer.

                Evaluate this answer:

                %s

                Give:
                1. Score out of 10
                2. Feedback
                3. Next Question
                """
                .formatted(answer);

        return chatClient.prompt()
                .user(prompt)
                .call()
                .content();
    }
}
