package com.etienne.client.store.model.exception;

public class EMailAddressNotFoundException extends RuntimeException {

    public EMailAddressNotFoundException() {
        super("nincs email cím megadva");
    }

}
