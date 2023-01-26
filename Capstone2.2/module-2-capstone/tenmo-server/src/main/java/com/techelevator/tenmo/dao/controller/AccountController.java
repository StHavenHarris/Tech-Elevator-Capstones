package com.techelevator.tenmo.dao.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.techelevator.tenmo.dao.AccountDao;
import com.techelevator.tenmo.dao.UserDao;
import com.techelevator.tenmo.model.Account;

@RestController
@PreAuthorize("isAuthenticated()")
public class AccountController {

    AccountDao accountDao;
    UserDao userDao;

    
    public AccountController(AccountDao accountDao, UserDao userDao) {
        this.accountDao = accountDao;
        this.userDao = userDao;
    }


    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping(path="/balance", method = RequestMethod.GET)
    public ResponseEntity<Account> getMyAccount(Principal principal){
        try {
            return new ResponseEntity<>(accountDao.getAccount(principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("An error occured when attempting to get account (Controller): "+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
   
    @PreAuthorize("hasAnyRole('USER')")
    @RequestMapping(path = "/accounts", method = RequestMethod.GET)
    public ResponseEntity<List<Account>> getAccounts(Principal principal){

        try {
            return new ResponseEntity<>(accountDao.getAllOtherAccount(principal.getName()), HttpStatus.OK);
        } catch (Exception e) {
            System.err.println("An error occured when attempting to get account list: "+e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
            
        }
    }
}
