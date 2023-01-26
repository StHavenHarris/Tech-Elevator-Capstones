package com.techelevator.tenmo;


import java.math.BigDecimal;
import java.util.List;

import com.techelevator.tenmo.model.Account;

import com.techelevator.tenmo.model.AuthenticatedUser;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;
import com.techelevator.tenmo.services.AccountService;
import com.techelevator.tenmo.services.AuthenticationService;
import com.techelevator.tenmo.services.ConsoleService;
import com.techelevator.tenmo.services.TransferService;

public class App {


    private static final String API_BASE_URL = "http://localhost:8080";

    private final ConsoleService consoleService = new ConsoleService();
    private final AuthenticationService authenticationService = new AuthenticationService(API_BASE_URL);
    
    

    /** Receiving account information from server service */
    private AccountService accountService;

    
     /** Receiving transfer informaiton from server service */
    private TransferService transferService;

    
    private AuthenticatedUser currentUser;

    

    /** All other account storage */
    private List<Account> accounts;

    /** User account storage */
    private Account myAccount;



    public static void main(String[] args) {
        App app = new App();
        app.run();
    }

    private void run() {
        consoleService.printGreeting();
        loginMenu();
        if (currentUser != null) {
            mainMenu();
        }
    }
    private void loginMenu() {
        int menuSelection = -1;
        while (menuSelection != 0 && currentUser == null) {
            consoleService.printLoginMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                handleRegister();
            } else if (menuSelection == 2) {
                handleLogin();
            } else if (menuSelection != 0) {
                System.out.println("Invalid Selection");
                consoleService.pause();
            }
        }
    }

    private void handleRegister() {
        System.out.println("Please register a new user account");
        UserCredentials credentials = consoleService.promptForCredentials();

        /** If user registration fails */

        try {

            if (authenticationService.register(credentials)) {
                System.out.println("Registration successful. You can now login.");
            } else {
                consoleService.printErrorMessage();
            }
            
        } catch (Exception e) {
            System.err.println("User registration failed: "+e.getMessage());
        }

        
    }

    private void handleLogin() {
        UserCredentials credentials = consoleService.promptForCredentials();

        /** In case of authentication failure, recover gracefully rather than crashing. */
        try {
            currentUser = authenticationService.login(credentials);

            if (currentUser == null) {
                consoleService.printErrorMessage();
            } else {
                
                
                accountService = new AccountService(API_BASE_URL, currentUser.getToken());
                transferService = new TransferService(API_BASE_URL, currentUser.getToken());
    
                
                this.myAccount = accountService.getMyAccount();
                this.accounts = accountService.getOtherAccounts();
                
            }
        } catch (Exception e) {
            System.err.println("\nInvalid Username or Password");
        }


        
        
    }

    

    private void mainMenu() {
        int menuSelection = -1;
        while (menuSelection != 0) {
            consoleService.printMainMenu();
            menuSelection = consoleService.promptForMenuSelection("Please choose an option: ");
            if (menuSelection == 1) {
                viewCurrentBalance();
            } else if (menuSelection == 2) {
                viewTransferHistory();
            } else if (menuSelection == 3) {
                viewPendingRequests();
            } else if (menuSelection == 4) {
                sendBucks();
            } else if (menuSelection == 5) {
                requestBucks();
            } else if (menuSelection == 0) {
                continue;
            } else {
                System.out.println("Invalid Selection");
            }

            
            consoleService.pause();
        }
    }

    /////////////////////////////////  Project Code ///////////////////////////////////////////////


    

    /** Initialize User account storage and retrieve account balance */

	private void viewCurrentBalance() {
        consoleService.printBalance(this.myAccount = accountService.getMyAccount());
         
	}

    /** Use Console Service to construct menu to view & access transfers */

	private void viewTransferHistory() {
        List<Transfer> transfers = null;
        consoleService.printTransferMenu(transfers = transferService.getTransferHistory(), myAccount);
        if(transfers != null){
            consoleService.printTransferMenu(transfers, accountService.getMyAccount());
            int transferId = consoleService.promptForMenuSelection("Please enter transfer ID to view details (0 to cancel): ");
            if(transferId > 0) {
                Transfer transfer = transferService.getTransferById(transferId);
                if(transfer != null){
                    consoleService.printTransfer(transfer);
                } else {
                    consoleService.printErrorMessage();
                }
            }
        }
	}

    /** Use Console Service to construct pending transfer menu & details */
	private void viewPendingRequests() {
        
		List<Transfer> transfers = transferService.getPendingTransfers();
        if(transfers != null){
            consoleService.printTransferMenu(transfers, myAccount);
            int transferId = consoleService.promptForMenuSelection("Please enter pending transfer ID to view details (0 to cancel): ");
            if(transferId > 0) {
                Transfer transfer = transferService.getTransferById(transferId);
                if(transfer != null && transfer.getTransferStatus().equals(Transfer.PENDING)){
                    consoleService.printTransfer(transfer);
                    consoleService.printPendingMenu();
                    int status = consoleService.promptForMenuSelection("Please choose an option: ");
                    if(status == 1){
                        transferService.approveTransfer(transfer.getFromAccount(), transfer.getFromAccountId(), 
                        transfer.getToAccount(), transfer.getToAccountId(), transfer.getAmount());

                        consoleService.printTransferMessage(transfer.getAmount(),transfer.getToAccount() );
                        viewCurrentBalance();


                    } else if(status == 2) {
                        transferService.rejectTransfer(transfer.getFromAccount(), transfer.getFromAccountId(), 
                        transfer.getToAccount(), transfer.getToAccountId(), transfer.getAmount());
                        consoleService.printTransferRejectedMessage(transfer.getToAccount());

                    } else if (status == 0) {
                        
                    } else {
                        consoleService.printErrorMessage();
                    }

                } else {
                    consoleService.printErrorMessage();
                }
            }
        }
		
	}

    /** Construct Send information */
	private void sendBucks() {

        this.accounts = accountService.getOtherAccounts();
        this.myAccount = accountService.getMyAccount();
        if(accounts != null){
            consoleService.printAccountMenu(accounts);
            int userId = consoleService.promptForMenuSelection("\n\nEnter ID of user you are sending to (0 to cancel): ");
            if(userId > 0){
                Account toAccount = accountService.getAccountByUserId(Long.valueOf(userId));
                if(toAccount != null) {
                    BigDecimal amount = consoleService.promptForBigDecimal("Enter amount: ");
                    if(amount.compareTo(new BigDecimal(0)) > 0 &&
                    myAccount.checkingFunds(amount)) {
                        if(transferService.sendTransfer(myAccount.getUsername(), myAccount.getAccountId(),toAccount.getUsername(),toAccount.getAccountId(),amount)) {
                            consoleService.printTransferMessage(amount,toAccount.getUsername() );
                            viewCurrentBalance();
                            
                        } else {
                            consoleService.printErrorMessage();
                        }
                    }
                } else {
                    consoleService.printErrorMessage();
                }
            }
        }

       
       

		
	}

    /** Construct Request information */ 
	private void requestBucks() {
		
        this.accounts = accountService.getOtherAccounts();
        this.myAccount = accountService.getMyAccount();
        if(accounts != null){
            consoleService.printAccountMenu(accounts);
            int userId = consoleService.promptForMenuSelection("\n\nEnter ID of user you are requesting from (0 to cancel): ");
            if(userId > 0){
                Account fromAccount = accountService.getAccountByUserId(Long.valueOf(userId));
                if(fromAccount != null) {
                    BigDecimal amount = consoleService.promptForBigDecimal("Enter amount: ");
                    if(amount.compareTo(new BigDecimal(0)) > 0 &&
                    fromAccount.checkingFunds(amount)) {
                        if(transferService.requestTransfer(fromAccount.getUsername(),fromAccount.getAccountId(),myAccount.getUsername(), myAccount.getAccountId(),amount)) {
                            consoleService.printTransferRequestMessage(amount, fromAccount.getUsername());
                            
                        } else {
                            consoleService.printErrorMessage();
                        }
                    } else {
                        consoleService.printErrorMessage();
                    }
                } else {
                    consoleService.printErrorMessage();
                }
            }
        }
		
	}
}
