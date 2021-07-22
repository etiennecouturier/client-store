package com.etienne.client.store.model.exception;

public class ClientNotFoundException extends RuntimeException {

    public ClientNotFoundException() {
        super("ügyfél nem található");
    }

}
