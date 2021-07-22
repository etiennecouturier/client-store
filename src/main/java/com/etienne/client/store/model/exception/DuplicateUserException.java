package com.etienne.client.store.model.exception;

public class DuplicateUserException extends RuntimeException {

    public DuplicateUserException() {
        super("a felhasználó már létezik");
    }

}
