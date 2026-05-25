package com.springboot.assistant.controller;

import com.springboot.assistant.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/documents")
@RequiredArgsConstructor
public class DocumentController {

    private final PdfService pdfService;

    @PostMapping("/upload")
    public String upload(
            @RequestParam MultipartFile file)
            throws Exception {

        pdfService.processPdf(file);

        return "Document uploaded successfully";
    }
}
