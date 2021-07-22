package com.etienne.client.store.model.exception;

public class AuthException extends RuntimeException {

    public AuthException() {
        super("helytelen felhasználónév vagy jelszó");
    }

}
