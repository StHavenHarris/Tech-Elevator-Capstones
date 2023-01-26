package com.techelevator.tenmo.dao;

import java.util.List;

import com.techelevator.tenmo.model.Account;;

public interface AccountDao {
    
    public Account getAccount(String username);
    public List<Account> getAllOtherAccount(String username);
    public Integer findIdByUsername(String username);
     
}
