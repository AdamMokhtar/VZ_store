package com.store.storeAPI.exception;

public class EmailNotFound  extends RuntimeException {
    public EmailNotFound(String message){
        super(message);
    }
}
