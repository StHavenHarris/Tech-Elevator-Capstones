package com.techelevator.tenmo.model;

import java.math.BigDecimal;

public class Transfer {
//Initialize
    private int transferId;
    private String transferType;
    private String transferStatus;
    private String fromAccount;
    private int fromAccountId;
    private String toAccount;
    private int toAccountId;
    private BigDecimal amount;

//Transfer type variables
    public static final String REQUEST = "Request";
    public static final String SEND = "Send";

    public static final String PENDING = "Pending";
    public static final String APPROVED = "Approved";
    public static final String REJECTED = "Rejected";

    public Transfer(){
        
    }

    
//Constructors
    public Transfer(String transferType, String transferStatus, String fromAccount, int fromAccountId, String toAccount,
            int toAccountId, BigDecimal amount) {
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.fromAccount = fromAccount;
        this.fromAccountId = fromAccountId;
        this.toAccount = toAccount;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }



    public Transfer(String transferType, String transferStatus, int fromAccountId, int toAccountId,
            BigDecimal amount) {
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.fromAccountId = fromAccountId;
        this.toAccountId = toAccountId;
        this.amount = amount;
    }
    
    public Transfer(int transferId, String transferType, String transferStatus, String accountFrom, String accountTo,
            BigDecimal amount) {
        this.transferId = transferId;
        this.transferType = transferType;
        this.transferStatus = transferStatus;
        this.fromAccount = accountFrom;
        this.toAccount = accountTo;
        this.amount = amount;
    }

    
//Getters and Setters
    public int getTransferId() {
        return transferId;
    }

    public String getTransferType() {
        return transferType;
    }

    public String getTransferStatus() {
        return transferStatus;
    }

    public String getFromAccount() {
        return fromAccount;
    }

    public int getFromAccountId() {
        return fromAccountId;
    }

    public String getToAccount() {
        return toAccount;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public static String getRequest() {
        return REQUEST;
    }

    public static String getSend() {
        return SEND;
    }

    public static String getPending() {
        return PENDING;
    }

    public static String getApproved() {
        return APPROVED;
    }

    public static String getRejected() {
        return REJECTED;
    }

    public void setTransferType(String transferType) {
        this.transferType = transferType;
    }

    public void setTransferStatus(String transferStatus) {
        this.transferStatus = transferStatus;
    }

    public void setFromAccount(String fromAccount) {
        this.fromAccount = fromAccount;
    }

    public void setFromAccountId(int fromAccountId) {
        this.fromAccountId = fromAccountId;
    }

    public void setToAccount(String toAccount) {
        this.toAccount = toAccount;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    


    


    
}
