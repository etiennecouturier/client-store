package com.etienne.client.store.controller;

import com.etienne.client.store.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping(path = "/pdf")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PdfController {

    private final PdfService pdfService;

    @GetMapping(path = "/fields")
    public List<String> findFields() throws IOException {
        return pdfService.findFields();
    }

}
