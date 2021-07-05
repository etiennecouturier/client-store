package com.etienne.client.store.service;

import com.etienne.client.store.model.domain.Client;
import com.etienne.client.store.model.exception.ClientNotFoundException;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.Collectors.toList;
import static java.util.stream.StreamSupport.stream;
import static org.apache.pdfbox.pdmodel.PDDocument.load;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PdfService {

    private final ClientService clientService;

    public List<String> findFields() throws IOException {
        return stream(spliteratorUnknownSize(
                getAcroForm()
                        .getFieldTree()
                        .iterator(), ORDERED), false)
                .map(PDField::getFullyQualifiedName)
                .collect(toList());
    }

    public ByteArrayInputStream downloadPdf(String clientId) throws ClientNotFoundException, IOException {
        Client client = clientService.findClientById(clientId);
        PDDocument pdf = load(PdfService.class.getResourceAsStream("/form.pdf"));
        PDAcroForm form = pdf
                .getDocumentCatalog()
                .getAcroForm();
        form.getField("name").setValue(client.getName());
        form.getField("tel").setValue(client.getTel());

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pdf.save(out);
        pdf.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private PDAcroForm getAcroForm() throws IOException {
        return load(PdfService.class.getResourceAsStream("/form.pdf"))
                .getDocumentCatalog()
                .getAcroForm();
    }

}
