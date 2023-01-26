package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Account {
    //Initialize variables
    private int accountId;
    private Long userId;
    private BigDecimal balance;
    private String username;
    

    public Account() {

    }

//Constructor
    public Account(int accountId, Long userId, BigDecimal balance, String username) {
        this.accountId = accountId;
        this.userId = userId;
        this.balance = balance;
        this.username = username;
    }

    
//Getters and Setters
    public int getAccountId() {
        return accountId;
    }


    public Long getUserId() {
        return userId;
    }


    public BigDecimal getBalance() {
        return balance;
    }


    public String getUsername() {
        return username;
    }


    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }


    public void setUserId(Long userId) {
        this.userId = userId;
    }


    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    //Check amount
    public boolean checkingFunds(BigDecimal amount){
        return (balance.subtract(amount)
            .compareTo(new BigDecimal(0)) >= 0) ? true: false;
    }
//Subtract from amount
    public void deductFunds(BigDecimal amount){
        this.balance = this.balance.subtract(amount);
    }

    

    

    



}
