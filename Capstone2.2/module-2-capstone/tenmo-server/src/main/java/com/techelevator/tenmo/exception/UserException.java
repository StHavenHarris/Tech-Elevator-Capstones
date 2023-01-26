package com.techelevator.tenmo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "User Not Found")

public class UserException extends Exception {
    private static final long serialVersionUID = 1L;

    public UserException(){
        super("User Not Found");
    }

    public UserException(String message){
        super(message);
    }
}