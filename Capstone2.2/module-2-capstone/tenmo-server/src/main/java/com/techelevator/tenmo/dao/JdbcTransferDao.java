package com.techelevator.tenmo.dao;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.techelevator.tenmo.exception.TransferException;
import com.techelevator.tenmo.model.Transfer;

@Component
public class JdbcTransferDao implements TransferDao{

    private JdbcTemplate jdbcTemplate;

    public JdbcTransferDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
//Proccess transfers
    @Override
    public boolean processTransfer(Transfer transfer) throws TransferException {
        
        boolean success = false;

        

        BigDecimal amount = transfer.getAmount();
        String transferType = transfer.getTransferType();
        String transferStatus = transfer.getTransferStatus();
        int fromAccountId = transfer.getFromAccountId();
        int toAccountId = transfer.getToAccountId();
        
        String sql = "insert into transfer (transfer_type_id,transfer_status_id,account_from,account_to,amount) " +
                    "values ((select transfer_type_id from transfer_type where transfer_type_desc = ?), "+
                            "(select transfer_status_id from transfer_status where transfer_status_desc = ?), " +
                                "?,?,?);";

        

        String fromSql = "update account set balance = balance - ? where account_id = ?";

        String toSql = "update account set balance = balance + ? where account_id = ?";

        String rqst = "update transfer set transfer_status_id = (select transfer_status_id from transfer_status where transfer_status_desc = ?)"+
                    "where transfer.account_from = ? and transfer.account_to = ? "+
                    "and transfer.transfer_type_id = (select transfer_type_id from transfer_type where transfer_type_desc = ?)";


                    
        int numberOfRowsUpdated = 0;


        try {
            if(transferType.equals(Transfer.SEND)) {
                if(transferStatus.equals(Transfer.APPROVED)) {
            
                    numberOfRowsUpdated += jdbcTemplate.update(sql,transferType,transferStatus,fromAccountId,toAccountId,amount);
                    numberOfRowsUpdated += jdbcTemplate.update(fromSql, amount, fromAccountId);
                    numberOfRowsUpdated += jdbcTemplate.update(toSql, amount, toAccountId);
                    
                    if(numberOfRowsUpdated==3)
                        success =true;

    
                }
            } else if(transferType.equals(Transfer.REQUEST)) {

                if(transferStatus.equals(Transfer.PENDING)) {

                    numberOfRowsUpdated += jdbcTemplate.update(sql,transferType,transferStatus,fromAccountId,toAccountId,amount);

                    if(numberOfRowsUpdated>0)
                        success =true;

                } else {

                    numberOfRowsUpdated += jdbcTemplate.update(rqst,transferStatus,fromAccountId,toAccountId,transferType);

                    if(transferStatus.equals(Transfer.APPROVED)){
                        numberOfRowsUpdated += jdbcTemplate.update(fromSql, amount, fromAccountId);
                        numberOfRowsUpdated += jdbcTemplate.update(toSql, amount, toAccountId);
                    }

                    if(numberOfRowsUpdated>0)
                        success =true;


                } 
            } else {
                    success = false;
                }
            
        } catch (Exception e) {
            System.err.println("Transfer failed! "+e.getMessage());
        }
        
        return success;
    }
//Get transfer List
    @Override
    public List<Transfer> listOfTransfers(int accountId) {

        String sql = "select transfer_id, transfer_type_desc AS \"transfer_type\", transfer_status_desc AS \"transfer_status\",  "+
        "from_user.username AS \"account_from\",from_acc.account_id AS \"account_from_id\", to_user.username AS \"account_to\", to_acc.account_id \"account_to_id\",  amount FROM transfer "+
        "join transfer_type ON transfer_type.transfer_type_id = transfer.transfer_type_id "+
        "join transfer_status ON transfer_status.transfer_status_id = transfer.transfer_status_id "+
        "join account to_acc ON to_acc.account_id = transfer.account_to "+
        "join account from_acc ON from_acc.account_id = transfer.account_from "+
        "join tenmo_user from_user ON from_user.user_id = from_acc.user_id "+
        "join tenmo_user to_user ON to_user.user_id = to_acc.user_id "+
        "where from_acc.account_id=? OR to_acc.account_id =?";

        

       

        List<Transfer> transfers = new ArrayList<>();

        SqlRowSet results = jdbcTemplate.queryForRowSet(sql,accountId, accountId);
        while(results.next()) {
            Transfer transfer = mapRowToTransfer(results);
            transfers.add(transfer);
        }
        return transfers;
        
        
    }



    private Transfer mapRowToTransfer(SqlRowSet rs) {

        
        return new Transfer(rs.getInt("transfer_id"), 
                            rs.getString("transfer_type"), 
                            rs.getString("transfer_status"), 
                            rs.getString("account_from"),
                            rs.getInt("account_from_id"),
                            rs.getString("account_to"),
                            rs.getInt("account_to_id"),
                            rs.getBigDecimal("amount")); 



    }


    
    

    
    
}

 
