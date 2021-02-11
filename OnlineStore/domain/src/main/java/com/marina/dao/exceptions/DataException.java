package com.marina.dao.exceptions;

public class DataException extends RuntimeException {
    public DataException(String msg){
        super(msg);
    }
    DataException(String msg, Exception e){
        super(msg, e);
    }
}
