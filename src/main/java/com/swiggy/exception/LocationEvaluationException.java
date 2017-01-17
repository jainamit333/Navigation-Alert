package com.swiggy.exception;

/**
 * Created by amit on 16/1/17.
 */
public class LocationEvaluationException extends Exception {

    public LocationEvaluationException(){
        super();
    }

    public LocationEvaluationException(String message){
        super(message);
    }

    public LocationEvaluationException(Exception e){
        super(e);
    }

}
