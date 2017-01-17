package com.swiggy.exception;

/**
 * Created by amit on 16/1/17.
 */
public class MethodNotImplementedException extends RuntimeException {

    public MethodNotImplementedException(){
        super();
    }

    public MethodNotImplementedException(String message){
        super(message);
    }

    public MethodNotImplementedException(Exception e){
        super(e);
    }
}
