package com.techelevator.tenmo.dao.controller;



import java.security.Principal;
import java.util.List;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.TransferDao;
import com.techelevator.tenmo.exception.TransferException;
import com.techelevator.tenmo.model.Transfer;


@RestController
@PreAuthorize("isAuthenticated()")
@RequestMapping("/transfer")
public class TransferController {

   

    private TransferDao transferDao;
    private AccountDao accountDao;

    public TransferController(TransferDao transferDao, AccountDao accountDao) {
        this.transferDao = transferDao;
        this.accountDao = accountDao;
    }

    @PreAuthorize("hasAnyRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/send", method = RequestMethod.POST)
    public boolean sendTransfer(@Valid @RequestBody Transfer transfer) throws TransferException{
        return transferDao.processTransfer(transfer);
    }
    
    @PreAuthorize("hasAnyRole('USER')")
    @ResponseStatus(HttpStatus.CREATED)
    @RequestMapping(path = "/request", method = RequestMethod.POST)
    public boolean requestTransfer(@Valid @RequestBody Transfer transfer) throws TransferException {
        return transferDao.processTransfer(transfer);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping(path = "/approve", method = RequestMethod.PUT)
    public boolean approveTransfer(@Valid @RequestBody Transfer transfer) throws TransferException {
        return transferDao.processTransfer(transfer);
    }

    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping(path = "/reject", method = RequestMethod.PUT)
    public boolean rejectTransfer(@Valid @RequestBody Transfer transfer) throws TransferException {
        return transferDao.processTransfer(transfer);
    }


    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping(path = "/list", method = RequestMethod.GET)
    public ResponseEntity<List<Transfer>> listOfTransfers(Principal principal){

        try {
            return new ResponseEntity<>(transferDao.listOfTransfers(accountDao.getAccount(principal.getName()).getAccountId()), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("An error occured when attempting to get transfer list: "+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
    
}
