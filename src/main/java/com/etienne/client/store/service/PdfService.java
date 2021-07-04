package com.etienne.client.store.service;

import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.apache.pdfbox.pdmodel.interactive.form.PDTerminalField;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;
import static org.apache.pdfbox.pdmodel.PDDocument.load;

@Service
public class PdfService {

    public List<String> findFields() throws IOException {
        return stream(
                spliteratorUnknownSize(
                        load(PdfService.class.getResourceAsStream("/form.pdf"))
                                .getDocumentCatalog()
                                .getAcroForm()
                                .getFieldTree()
                                .iterator(), ORDERED
                ), false
        ).filter(pdField -> pdField instanceof PDTerminalField)
                .map(PDField::getFullyQualifiedName)
                .collect(Collectors.toList());
    }


}
