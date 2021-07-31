package com.etienne.client.store.model.exception;

public class UserPhotoNotFoundException extends RuntimeException {
    public UserPhotoNotFoundException() {
        super("A felhasználói fotó nem található");
    }
}
