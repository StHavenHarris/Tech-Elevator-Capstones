package com.techelevator.tenmo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus( code = HttpStatus.BAD_REQUEST, reason = "Transfer Failed")
public class TransferException extends Exception{

    private static final long serialVersionUID = 1L;

    public TransferException(){
        super("Transer failed!");
    }

    public TransferException(String message){
        super(message);
    }


    
}
