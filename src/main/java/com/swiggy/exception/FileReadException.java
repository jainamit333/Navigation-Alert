package com.swiggy.exception;

import com.swiggy.service.FileReaderService;

/**
 * Created by amit on 16/1/17.
 */
public class FileReadException extends Exception {

    public FileReadException(){
        super();
    }

    public FileReadException(String message){
        super(message);
    }

    public FileReadException(Exception e){
        super(e);
    }

}
