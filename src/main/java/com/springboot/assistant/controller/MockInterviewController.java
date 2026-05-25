package com.springboot.assistant.controller;

import com.springboot.assistant.service.MockInterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/interview")
@RequiredArgsConstructor
public class MockInterviewController {

    private final MockInterviewService service;

    @PostMapping("/start")
    public String start() {
        return service.startInterview();
    }

    @PostMapping("/answer")
    public String answer(
            @RequestBody String answer) {

        return service.evaluateAnswer(answer);
    }
}
