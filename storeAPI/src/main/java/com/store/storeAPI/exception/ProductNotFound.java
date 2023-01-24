package com.store.storeAPI.exception;

public class ProductNotFound extends RuntimeException{
    public ProductNotFound(String message){
        super(message);
    }
}