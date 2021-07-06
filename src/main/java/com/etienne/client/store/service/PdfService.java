package com.etienne.client.store.service;

import com.etienne.client.store.model.domain.ClientVisit;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

import static java.util.Spliterator.ORDERED;
import static java.util.Spliterators.spliteratorUnknownSize;
import static java.util.stream.StreamSupport.stream;
import static org.apache.pdfbox.Loader.loadPDF;
import static org.apache.pdfbox.pdmodel.font.PDType0Font.load;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class PdfService {

    private final ClientService clientService;

    /**
     * https://github.com/Valuya/fontbox/blob/master/examples/src/main/java/org/apache/pdfbox/examples/interactive/form/CreateSimpleFormWithEmbeddedFont.java
     */
    public ByteArrayInputStream downloadPdf(ObjectId visitId) throws IOException {
        ClientVisit client = clientService.findClientWithVisit(visitId);

        PDDocument pdf = loadPDF(PdfService.class.getResourceAsStream("/form.pdf"));
        PDAcroForm form = pdf.getDocumentCatalog().getAcroForm();
        PDType0Font font = load(pdf, PdfService.class.getResourceAsStream("/fonts/Helvetica-BoldOblique.ttf"), false);
        String defaultAppearanceString = "/" + form.getDefaultResources().add(font).getName() + " 11 Tf 0 g";

        stream(spliteratorUnknownSize(
                pdf.getDocumentCatalog().getAcroForm()
                        .getFieldTree()
                        .iterator(), ORDERED), false)
                .forEach(pdField -> ((PDTextField) (pdField)).setDefaultAppearance(defaultAppearanceString));

        form.getField("name").setValue(client.getName());
        form.getField("tel").setValue(client.getTel());
        form.getField("date").setValue(toStr(client.getVisit().getDate()));
        form.getField("rightSph").setValue(toStr(client.getVisit().getExam().getRightEye().getDioptria()));
        form.getField("rightCyl").setValue(toStr(client.getVisit().getExam().getRightEye().getCilinder()));
        form.getField("rightAxs").setValue(toStr(client.getVisit().getExam().getRightEye().getFok()));
        form.getField("rightAdd").setValue(toStr(client.getVisit().getExam().getRightEye().getVizus()));
        form.getField("leftSph").setValue(toStr(client.getVisit().getExam().getLeftEye().getDioptria()));
        form.getField("leftCyl").setValue(toStr(client.getVisit().getExam().getLeftEye().getCilinder()));
        form.getField("leftAxs").setValue(toStr(client.getVisit().getExam().getLeftEye().getFok()));
        form.getField("leftAdd").setValue(toStr(client.getVisit().getExam().getLeftEye().getVizus()));
        form.getField("frame").setValue(toStr(client.getVisit().getFees().getFrame()));
        form.getField("rightLense").setValue(toStr(client.getVisit().getFees().getRightLense()));
        form.getField("leftLense").setValue(toStr(client.getVisit().getFees().getLeftLense()));
        form.getField("service").setValue(toStr(client.getVisit().getFees().getService()));
        form.getField("exam").setValue(toStr(client.getVisit().getFees().getExam()));
        form.getField("other").setValue(toStr(client.getVisit().getFees().getOther()));
        form.getField("total").setValue(toStr(client.getVisit().getFees().getTotal()));

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pdf.save(out);
        pdf.close();
        return new ByteArrayInputStream(out.toByteArray());
    }
    
    private <T> String toStr(T obj) {
        return obj == null ? "" : obj.toString();
    }

}
