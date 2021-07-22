package com.etienne.client.store.model.exception;

public class PdfDownloadException extends RuntimeException {

    public PdfDownloadException() {
        super("pdf letöltése sikertelen");
    }

}
