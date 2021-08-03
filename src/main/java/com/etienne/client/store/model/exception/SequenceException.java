package com.etienne.client.store.model.exception;

public class SequenceException extends RuntimeException {

    public SequenceException() {
        super("a látogatás sorszáma nem generálható le");
    }
}
