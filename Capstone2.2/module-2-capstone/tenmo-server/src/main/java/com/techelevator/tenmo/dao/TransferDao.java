package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.exception.TransferException;
import com.techelevator.tenmo.model.Transfer;

public interface TransferDao {
    
    public boolean processTransfer(Transfer transfer) throws TransferException;
    public List<Transfer> listOfTransfers(int accountId);
}
