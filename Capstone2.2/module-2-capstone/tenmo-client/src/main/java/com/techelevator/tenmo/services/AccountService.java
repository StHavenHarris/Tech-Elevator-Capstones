package com.techelevator.tenmo.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestClientResponseException;
import org.springframework.web.client.RestTemplate;

import com.techelevator.tenmo.model.Account;
import com.techelevator.util.BasicLogger;

public class AccountService {
    
    private final String baseUrl;
    private final RestTemplate restTemplate = new RestTemplate();
    private final String authToken;

    private List<Account> otherAccounts;



    public AccountService(String url, String token) {
        this.baseUrl = url;
        this.authToken = token;

        otherAccounts = fetchOtherAccounts();

        
        
    }
    //Get users account
    private Account fetchMyAccount(){
        HttpEntity<?> entity = makeAccountEntity();
        try {
            ResponseEntity<Account> response = restTemplate.exchange(baseUrl+"/balance", HttpMethod.GET, entity, Account.class);
            return response.getBody();            
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
            return null;
        }
    }

    public Account getMyAccount(){

        //otherAccounts = fetchOtherAccounts();
        return fetchMyAccount();
    }
//Get other accounts
    private List<Account> fetchOtherAccounts(){
        HttpEntity<?> entity = makeAccountEntity();
        try {
            ResponseEntity<List<Account>> response = restTemplate.exchange(baseUrl+"/accounts", HttpMethod.GET, entity, new ParameterizedTypeReference<List<Account>>() {});
            return response.getBody();            
        } catch (RestClientResponseException | ResourceAccessException e) {
            BasicLogger.log(e.getMessage());
            return null;
        }
    }

    public List<Account> getOtherAccounts(){
        return fetchOtherAccounts();
    }
//Get user by ID
    public Account getAccountByUserId(Long userId){
        Account a = null;
        for(Account c: otherAccounts){
            if(c.getUserId().equals((userId))) {
                a = c;
                break;
            }
        }
        return a;
    }
//Get account by account ID
    public Account getAccountByAccountId(int accountId){
        Account a = null;
        for(Account c: otherAccounts){
            if(c.getAccountId() == accountId) {
                a = c;
                break;
            }
        }
        return a;
    }

    /* public boolean checkingFunds(BigDecimal amount){
        return (getBalance().subtract(amount)
            .compareTo(new BigDecimal(0)) >= 0) ? true: false;
    } */

    // public boolean isAccountValid(Long userId){
    //     return ((findAccountByUserId(userId) != null) && 
    //         !getMyAccount().getUserId().equals(userId)) ? true: false;
    // }

    // public int getAccountId(Long userId){
    //     return /* (getMyAccount().getUserId().equals(userId)) ? 
    //     getMyAccount().getAccountId() :  */findAccountByUserId(userId).getAccountId(); 
    // }

    // public int getMyAccountId(){
    //     return getMyAccount().getAccountId();
    // }


//Return Balance
    public BigDecimal getBalance(){
        return fetchMyAccount().getBalance();
    }

    public BigDecimal remainingBalance(BigDecimal amount){
        return getBalance().subtract(amount);
    }


    

    
    
    private HttpEntity<Void> makeAccountEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(authToken);
        return new HttpEntity<>(headers);
    }
    
}
