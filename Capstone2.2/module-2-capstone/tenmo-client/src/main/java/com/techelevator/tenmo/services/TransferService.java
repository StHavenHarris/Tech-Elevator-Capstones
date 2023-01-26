package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.Transfer;
import com.techelevator.util.BasicLogger;

public class TransferService {
//Initialize

    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();

    private final String authToken;

    private List<Transfer> transferList = null;


    public TransferService(String url , String token){
        this.baseUrl = url;
        this.authToken = token;
    }

//Send money
    public boolean sendTransfer(String fromAccount, int fromAccountId, String toAccount, int toAccountId,  BigDecimal amount){
        return add(new Transfer(Transfer.SEND, Transfer.APPROVED,fromAccount,fromAccountId,toAccount,toAccountId,amount ));
    }
//Request Money
    public boolean requestTransfer(String fromAccount, int fromAccountId, String toAccount, int toAccountId,  BigDecimal amount){
        return add(new Transfer(Transfer.REQUEST, Transfer.PENDING,fromAccount,fromAccountId,toAccount,toAccountId,amount ));
    }
//Reject Request
    public boolean rejectTransfer(String fromAccount, int fromAccountId, String toAccount, int toAccountId,  BigDecimal amount ) {
        return add(new Transfer(Transfer.REQUEST, Transfer.REJECTED,fromAccount,fromAccountId,toAccount,toAccountId,amount ));
    }
    //Approve Transfer
    public boolean approveTransfer(String fromAccount, int fromAccountId, String toAccount, int toAccountId,  BigDecimal amount ) {
        return add(new Transfer(Transfer.REQUEST, Transfer.APPROVED,fromAccount,fromAccountId,toAccount,toAccountId,amount ));
    }
//Transfer History
    public List<Transfer> getTransferHistory(){
        HttpEntity<?> entity = makeTransferEntity();
        try {
            ResponseEntity<List<Transfer>> response = restTemplate.exchange(baseUrl+"/transfer/list", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Transfer>>() {});
            return transferList = response.getBody();            
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
            return null;
        }
    }
//Pending transfer
    public List<Transfer> getPendingTransfers(){
        List<Transfer> list = new ArrayList<>();
        getTransferHistory();
        for(Transfer t: transferList){
            if(t.getTransferStatus().equals(Transfer.PENDING)){
                list.add(t);
            }
        }

        return list;
    }
    
    
//Get Transfer by ID
    public Transfer getTransferById(int transferId){
        Transfer transfer = null;
        for(Transfer t: getTransferHistory()){
            if(t.getTransferId() == transferId) {
                transfer = t;
                break;
            }
        }
        return transfer;
    }

//Add transfer
    private boolean add(Transfer transfer) {

        
        HttpEntity<Transfer> requestEntity= makeTransferEntity(transfer);
        boolean success = false;

        try {

            if(transfer.getTransferType().equals(Transfer.SEND)){
                if(transfer.getTransferStatus().equals(Transfer.APPROVED)){
                    success = restTemplate.postForObject(baseUrl+"/transfer/send", requestEntity, Boolean.class);
                }

            } else if (transfer.getTransferType().equals(Transfer.REQUEST)){

                if(transfer.getTransferStatus().equals(Transfer.APPROVED)){
                    try {
                        
                        restTemplate.put(baseUrl+"/transfer/approve", requestEntity);
                        success = true; 
                        
                    } catch (Exception e) {
                        System.err.println("\nUnable to complete transfer: "+e.getMessage());
                    }
                    
                    
                } else {
                    
                    try {
                        
                        restTemplate.put(baseUrl+"/transfer/reject", requestEntity);
                        success = true; 
                        
                    } catch (Exception e) {
                        System.err.println("\nUnable to complete transfer: "+e.getMessage());
                    }
                }
            }

        } catch (RestClientResponseException e) {
            BasicLogger.log(e.getRawStatusCode()+ " : "+e.getStatusText());
        } catch (ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
        }


        return success;
        

    }

    private HttpEntity<Void> makeTransferEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }



    private HttpEntity<Transfer> makeTransferEntity(Transfer transfer) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(transfer, headers);
    }

    
}
