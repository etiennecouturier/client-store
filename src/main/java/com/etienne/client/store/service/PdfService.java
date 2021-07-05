package com.etienne.client.store.service;

import com.etienne.client.store.model.domain.ClientVisit;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;
import org.bson.types.ObjectId;
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

    public ByteArrayInputStream downloadPdf(ObjectId visitId) throws IOException {
        ClientVisit client = clientService.findClientWithVisit(visitId);
        PDDocument pdf = load(PdfService.class.getResourceAsStream("/form.pdf"));
        PDAcroForm form = pdf.getDocumentCatalog().getAcroForm();
        form.getField("name").setValue(client.getName());
        form.getField("tel").setValue(client.getTel());
        form.getField("date").setValue(toStr(client.getVisit().getDate()));
        form.getField("rightSph").setValue(toStr(client.getVisit().getExam().getRightEye().getDioptria()));
        form.getField("rightCyl").setValue(toStr(client.getVisit().getExam().getRightEye().getCilinder()));
        form.getField("rightAxs").setValue(toStr(client.getVisit().getExam().getRightEye().getFok()));
        form.getField("rightAdd").setValue(toStr(client.getVisit().getExam().getRightEye().getVizus()));
        form.getField("leftSph").setValue(toStr(client.getVisit().getExam().getLeftEye().getDioptria()));
        form.getField("leftCyl").setValue(toStr(client.getVisit().getExam().getLeftEye().getDioptria()));
        form.getField("leftAxs").setValue(toStr(client.getVisit().getExam().getLeftEye().getDioptria()));
        form.getField("leftAdd").setValue(toStr(client.getVisit().getExam().getLeftEye().getDioptria()));
        form.getField("frame").setValue(toStr(client.getVisit().getFees().getFrame()));
        form.getField("rightLense").setValue(toStr(client.getVisit().getFees().getRightLense()));
        form.getField("leftLense").setValue(toStr(client.getVisit().getFees().getLeftLense()));
        form.getField("service").setValue(toStr(client.getVisit().getFees().getService()));
        form.getField("exam").setValue(toStr(client.getVisit().getFees().getExam()));
        form.getField("other").setValue(toStr(client.getVisit().getFees().getOther()));

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
    
    private <T> String toStr(T obj) {
        return obj == null ? "" : obj.toString();
    }

}
