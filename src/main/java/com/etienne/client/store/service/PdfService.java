package com.etienne.client.store.service;

import com.etienne.client.store.model.domain.ClientVisit;
import com.etienne.client.store.model.exception.PdfDownloadException;
import lombok.RequiredArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.font.PDType0Font;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDTextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import static java.time.format.DateTimeFormatter.ofPattern;
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
     * for font related infos:
     * https://github.com/Valuya/fontbox/blob/master/examples/src/main/java/org/apache/pdfbox/examples/interactive/form/CreateSimpleFormWithEmbeddedFont.java
     */
    public InputStream downloadPdf(String visitId) {
        try {
            ClientVisit client = clientService.findClientWithVisit(visitId);
            return downloadPdf(client);
        } catch (IOException e) {
            throw new PdfDownloadException();
        }
    }

    InputStream downloadPdf(ClientVisit client) throws IOException {
        PDDocument pdf = loadPDF(PdfService.class.getResourceAsStream("/form.pdf"));
        setFontForFields(pdf);
        fillForm(client, pdf);
        return writeToStream(pdf);
    }

    private void setFontForFields(PDDocument pdf) throws IOException {
        PDType0Font font = load(pdf, PdfService.class.getResourceAsStream("/fonts/Helvetica-BoldOblique.ttf"), false);
        String defaultAppearanceString = "/" + pdf.getDocumentCatalog().getAcroForm().getDefaultResources().add(font).getName() + " 9 Tf 0 g";
        stream(spliteratorUnknownSize(
                pdf.getDocumentCatalog().getAcroForm()
                        .getFieldTree()
                        .iterator(), ORDERED), false)
                .forEach(pdField -> ((PDTextField) (pdField)).setDefaultAppearance(defaultAppearanceString));
    }

    private void fillForm(ClientVisit client, PDDocument pdf) throws IOException {
        PDAcroForm form = pdf.getDocumentCatalog().getAcroForm();
        form.getField("name").setValue(client.getName());
        form.getField("tel").setValue(client.getTel());
        form.getField("seq").setValue(toStr(client.getVisit().getSeq()));
        form.getField("date").setValue(toStr(client.getVisit().getDate().format(ofPattern("yyyy.MM.dd"))));
        form.getField("rightSph").setValue(toStr(client.getVisit().getExam().getRightEye().getDioptria()));
        form.getField("rightCyl").setValue(toStr(client.getVisit().getExam().getRightEye().getCilinder()));
        form.getField("rightAxs").setValue(toStr(client.getVisit().getExam().getRightEye().getFok()));
        form.getField("rightAdd").setValue(toStr(client.getVisit().getExam().getRightEye().getAdd()));
        form.getField("rightPD").setValue(toStr(client.getVisit().getExam().getRightEye().getPd()));
        form.getField("rightBifoMag").setValue(toStr(client.getVisit().getExam().getRightEye().getBifoMag()));
        form.getField("leftSph").setValue(toStr(client.getVisit().getExam().getLeftEye().getDioptria()));
        form.getField("leftCyl").setValue(toStr(client.getVisit().getExam().getLeftEye().getCilinder()));
        form.getField("leftAxs").setValue(toStr(client.getVisit().getExam().getLeftEye().getFok()));
        form.getField("leftAdd").setValue(toStr(client.getVisit().getExam().getLeftEye().getAdd()));
        form.getField("leftPD").setValue(toStr(client.getVisit().getExam().getLeftEye().getPd()));
        form.getField("leftBifoMag").setValue(toStr(client.getVisit().getExam().getLeftEye().getBifoMag()));
        form.getField("frameFee").setValue(toStr(client.getVisit().getFees().getFrame()));
        form.getField("rightLense").setValue(toStr(client.getVisit().getFees().getRightLense()));
        form.getField("leftLense").setValue(toStr(client.getVisit().getFees().getLeftLense()));
        form.getField("service").setValue(toStr(client.getVisit().getFees().getService()));
        form.getField("exam").setValue(toStr(client.getVisit().getFees().getExam()));
        form.getField("other").setValue(toStr(client.getVisit().getFees().getOther()));
        form.getField("total").setValue(toStr(client.getVisit().getFees().getTotal()));
        form.getField("discountPercent").setValue(toStr(client.getVisit().getFees().getDiscountPercent()));
        form.getField("discountAmount").setValue(toStr(client.getVisit().getFees().getDiscountAmount()));
        form.getField("paid").setValue(toStr(client.getVisit().getFees().getPaid()));
        form.getField("toBePaid").setValue(toStr(client.getVisit().getFees().getToBePaid()));
        form.getField("frame").setValue(toStr(client.getVisit().getFrame()));
        form.getField("lense").setValue(toStr(client.getVisit().getLense()));
    }

    private InputStream writeToStream(PDDocument pdf) throws IOException {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        pdf.save(out);
        pdf.close();
        return new ByteArrayInputStream(out.toByteArray());
    }

    private <T> String toStr(T obj) {
        return obj == null ? "" : obj.toString();
    }

}
