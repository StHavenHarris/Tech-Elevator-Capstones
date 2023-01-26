package com.techelevator.tenmo.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.model.Account;

@Component
public class JdbcAccountDao implements AccountDao {

    private JdbcTemplate jdbcTemplate;

    public JdbcAccountDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//Get account
    @Override
    public Account getAccount(String username) {

        int userId = findIdByUsername(username);
        
        String sql = "select account.user_id, account_id, balance, tenmo_user.username from account "+
        "join tenmo_user ON tenmo_user.user_id = account.user_id "+
        "where account.user_id = ?";

        Account myAccount = null;
        
        try {

            SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);

            if(results.next())
                myAccount = mapRowToAccount(results);
            else
                System.err.println("No account found");
    
            }
            catch(Exception e){
                System.err.println("getMyAccount Exception\n"+e.getMessage());
                
            }

        return myAccount;

    }

    


   //Find username
    @Override
    public Integer findIdByUsername(String username) {
        String sql = "SELECT user_id FROM tenmo_user WHERE username ILIKE ?;";
        Integer id = jdbcTemplate.queryForObject(sql, Integer.class, username);
        if (id != null) {
            return id;
        } else {
            
            return -1;
        }
        
    }

   

    @Override
    public List<Account> getAllOtherAccount(String username) {
        
        int userId = findIdByUsername(username);
        List<Account> accounts = new ArrayList<>();

        String sql = "select account.user_id, account_id, account.balance, username from account "+
        "join tenmo_user ON tenmo_user.user_id = account.user_id "+
        "where account.user_id != ?";

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,userId);
        while(results.next()) {
            Account account = mapRowToAccount(results);
            accounts.add(account);
        }
        return accounts;

    }

    private Account mapRowToAccount(SqlRowSet rs) {
       
        Account account = new Account();
        account.setUserId(rs.getLong("user_id"));
        account.setAccountId(rs.getInt("account_id"));
        account.setBalance(rs.getBigDecimal("balance"));
        account.setUsername(rs.getString("username"));
        return account;
    }
    
}

    