package com.etienne.client.store.model.exception;

public class PrinterNotFoundException extends RuntimeException {

    public PrinterNotFoundException() {
        super("a nyomtató nem található");
    }
}
