package com.etienne.client.store.service;

import com.etienne.client.store.model.exception.PrinterNotFoundException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.printing.PDFPageable;
import org.springframework.stereotype.Service;

import javax.print.PrintService;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.Copies;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.util.Optional;

import static java.awt.print.PrinterJob.getPrinterJob;
import static java.util.Arrays.stream;
import static javax.print.PrintServiceLookup.lookupPrintServices;

@Service
public class PrinterService {

    public void print(PDDocument document) throws PrinterException {
        PrinterJob job = getPrinterJob();
        job.setPageable(new PDFPageable(document));
        job.setPrintService(
                findPrintService("...")
                        .orElseThrow(PrinterNotFoundException::new)
        );
        PrintRequestAttributeSet attributes = new HashPrintRequestAttributeSet();
        attributes.add(new Copies(3));
        job.print(attributes);
    }

    private static Optional<PrintService> findPrintService(String printerName) {
        return stream(lookupPrintServices(null, null))
                .filter(printService -> printerName.equals(printService.getName().trim()))
                .findFirst();
    }

}
