package com.marina.exceptions;

public class InitializationException extends RuntimeException {
    public InitializationException(String msg){
        super(msg);
    }
    public InitializationException(String msg, Exception e){
        super(msg, e);
    }
}
