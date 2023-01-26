package com.techelevator.tenmo.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.NOT_FOUND, reason = "Account Not Found")
public class AccountException extends Exception {
    private static final long serialVersionUID = 1L;

    
    public AccountException(){
        super("Account Not Found");
    }

    public AccountException(String message){
        super(message);
    }
}