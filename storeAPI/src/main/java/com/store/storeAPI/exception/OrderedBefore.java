package com.store.storeAPI.exception;

public class OrderedBefore extends RuntimeException {
    public OrderedBefore(String message){
        super(message);
    }
}