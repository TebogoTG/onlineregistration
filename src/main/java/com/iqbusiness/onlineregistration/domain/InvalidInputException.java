package com.iqbusiness.onlineregistration.domain;

public class InvalidInputException extends Exception{
    public InvalidInputException(String message){
        super(message);
    }
}
