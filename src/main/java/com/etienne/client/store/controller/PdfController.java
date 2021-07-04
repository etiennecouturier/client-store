package com.etienne.client.store.controller;

import com.etienne.client.store.model.exception.ClientNotFoundException;
import com.etienne.client.store.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping(path = "/download/{clientId}", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable String clientId) throws IOException, ClientNotFoundException {
        return ResponseEntity
                .ok()
                .body(new InputStreamResource(
                        pdfService.downloadPdf(clientId)
                ));
    }

}
