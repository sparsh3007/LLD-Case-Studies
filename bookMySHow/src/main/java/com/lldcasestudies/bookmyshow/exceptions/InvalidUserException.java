package com.lldcasestudies.bookmyshow.exceptions;

public class InvalidUserException extends RuntimeException{
    public InvalidUserException(String message){
        super(message);
    }
}
