package com.springboot.assistant.service;

import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class PdfService {

    private final DocumentIngestionService ingestionService;

    public void processPdf(
            MultipartFile file) throws Exception {

        PDDocument document =
                PDDocument.load(
                        file.getInputStream()
                );

        PDFTextStripper stripper =
                new PDFTextStripper();

        String text =
                stripper.getText(document);

        ingestionService.ingest(text);

        document.close();
    }
}
