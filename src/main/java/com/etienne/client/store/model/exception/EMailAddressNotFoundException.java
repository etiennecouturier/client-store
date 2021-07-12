package com.etienne.client.store.model.exception;

public class EMailAddressNotFoundException extends Exception {
    public EMailAddressNotFoundException() {
        super("Nincs email cím megadva");
    }
}
