package com.marina.exception;

public class CategoryInitializationException extends RuntimeException{
    public CategoryInitializationException(String msg){
        super(msg);
    }
    public CategoryInitializationException(String msg, Exception e){
        super(msg, e);
    }
}
