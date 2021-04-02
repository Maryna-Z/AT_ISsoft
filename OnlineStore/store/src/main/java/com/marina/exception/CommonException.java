package com.marina.exception;

public class CommonException extends RuntimeException{
    public CommonException(String msg){
        super(msg);
    }
    public CommonException(String msg, Exception e){
        super(msg, e);
    }
}
