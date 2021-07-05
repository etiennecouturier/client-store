package com.etienne.client.store.controller;

import com.etienne.client.store.model.exception.ClientNotFoundException;
import com.etienne.client.store.service.PdfService;
import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

@RestController
@RequestMapping(path = "/pdf")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
@CrossOrigin(origins = "http://localhost:4200")
public class PdfController {

    private final PdfService pdfService;

    @GetMapping(path = "/fields")
    public List<String> findFields() throws IOException {
        return pdfService.findFields();
    }

    @GetMapping(path = "/download/{visitId}", produces = APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> downloadPdf(@PathVariable ObjectId visitId) throws IOException, ClientNotFoundException {
        return ResponseEntity
                .ok()
                .body(new InputStreamResource(
                        pdfService.downloadPdf(visitId)
                ));
    }

}
