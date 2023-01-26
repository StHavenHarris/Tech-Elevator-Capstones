package com.techelevator.tenmo.services;


import com.techelevator.tenmo.model.Account;
import com.techelevator.tenmo.model.Transfer;
import com.techelevator.tenmo.model.UserCredentials;

import java.math.BigDecimal;
import java.util.List;
import java.util.Scanner;

public class ConsoleService {

    private final Scanner scanner = new Scanner(System.in);

    public int promptForMenuSelection(String prompt) {
        int menuSelection;
        System.out.print(prompt);
        try {
            menuSelection = Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            menuSelection = -1;
        }
        return menuSelection;
    }

    public void printGreeting() {
        printTitleCard();
        System.out.println("*********************");
        System.out.println("* Welcome to TEnmo! *");
        System.out.println("*********************");
    }

    public void printLoginMenu() {
        System.out.println();
        System.out.println("1: Register");
        System.out.println("2: Login");
        System.out.println("0: Exit");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println();
        System.out.println("1: View your current balance");
        System.out.println("2: View your past transfers");
        System.out.println("3: View your pending requests");
        System.out.println("4: Send TE bucks");
        System.out.println("5: Request TE bucks");
        System.out.println("0: Exit");
        System.out.println();
    }

    public UserCredentials promptForCredentials() {
        String username = promptForString("Username: ");
        String password = promptForString("Password: ");
        return new UserCredentials(username, password);
    }

    public String promptForString(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }

    public int promptForInt(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a number.");
            }
        }
    }

    public BigDecimal promptForBigDecimal(String prompt) {
        System.out.print(prompt);
        while (true) {
            try {
                return new BigDecimal(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Please enter a decimal number.");
            }
        }
    }

    public void pause() {
        System.out.println("\nPress Enter to continue...");
        scanner.nextLine();
    }

    public void printErrorMessage() {
        System.out.println("An error occurred. Check the log for details.");
    }

    /** Print Account Menu */
    public void printAccountMenu(List<Account> accounts){
        printAccounts(accounts);
        System.out.println("0: Exit");
        System.out.println();
    }

    /** Print Account List */
    public void printAccounts(List<Account> accounts){
        System.out.println("\n\n-------------------------------------------");
        System.out.println("Users");
        System.out.println("ID          Name");
        System.out.println("-------------------------------------------");
        for(Account a : accounts){
            System.out.println(a.getUserId()+"       "+a.getUsername());
        }
    }


    /** Print Transfer Menu */
    public void printTransferMenu(List<Transfer> tranfers, Account myAccount){
        printTransfers(tranfers, myAccount);
        System.out.println("\n0: Exit");
        System.out.println();
    }

    /** Print Transfer List */
    public void printTransfers(List<Transfer> transferList, Account myAccount){
        System.out.println("\n\n-------------------------------------------");
        System.out.println("Transfer");
        System.out.println("ID             From/To               Amount");
        System.out.println("-------------------------------------------");
        for(Transfer t : transferList){
            if(t.getFromAccount().equals(myAccount.getUsername())){
                System.out.println(t.getTransferId()+"          To: "+t.getToAccount()+"          $"+t.getAmount());
            } else {
                System.out.println(t.getTransferId()+"          From: "+t.getFromAccount()+"          $"+t.getAmount());
            }
        }
    }

    /** Print Transfer details */
    public void printTransfer(Transfer transfer){
            System.out.println("--------------------------------------------");
            System.out.println("Transfer Details                            ");
            System.out.println("--------------------------------------------");
            if(transfer == null){
                System.out.println("No transfer to print");
            } else {
                System.out.println("Id: "+transfer.getTransferId());
                System.out.println("From: "+transfer.getFromAccount());
                System.out.println("To: "+transfer.getToAccount());
                System.out.println("Type: "+transfer.getTransferType());
                System.out.println("Status: "+transfer.getTransferStatus());
                System.out.println("Amount: "+transfer.getAmount());
            }
    }

    /** Print Pending transfer options */
    public void printPendingMenu(){

        System.out.println("\n--------------------------");
        System.out.println("1: Approve");
        System.out.println("2: Reject");
        System.out.println("0: Don't approve or reject");
        System.out.println("--------------------------\n");

    }

    /** Print Approved Transfer message */
    public void printTransferMessage(BigDecimal amount, String username){
        System.out.println("\nYou have successfully transfered amount: "+ amount +" from your account to "+username);
    }

    /** Print Rejected Transfer Message */
    public void printTransferRejectedMessage(String username) {
        System.out.println("\nYou have rejected transfer request from "+username);
    }

    /** Print Requested Transfer Message */
    public void printTransferRequestMessage(BigDecimal amount, String fromAccount){
        System.out.println("\nYou have request transfered amount: "+ amount +" from "+fromAccount);
    }

    /** Print Balance */
    public void printBalance(Account myAccount){
        System.out.println("\nYour current account balance is: $"+myAccount.getBalance());
    }

    /** Print Tile Card */
    public void printTitleCard(){
        System.out.println("\n\n /$$$$$$$$                                                   /$$$$$$$                      /$$              /$$$$$$                     ");
        System.out.println("|__  $$__/                                                  | $$__  $$                    | $$             /$$__  $$                    ");
        System.out.println("   | $$     /$$$$$$  /$$$$$$$  /$$$$$$/$$$$   /$$$$$$       | $$  \\ $$  /$$$$$$  /$$$$$$$ | $$   /$$      | $$  \\ $$  /$$$$$$   /$$$$$$");
        System.out.println("   | $$    /$$__  $$| $$__  $$| $$_  $$_  $$ /$$__  $$      | $$$$$$$  |____  $$| $$__  $$| $$  /$$/      | $$$$$$$$ /$$__  $$ /$$__  $$");
        System.out.println("   | $$   | $$$$$$$$| $$  \\ $$| $$ \\ $$ \\ $$| $$  \\ $$      | $$__  $$  /$$$$$$$| $$  \\ $$| $$$$$$/       | $$__  $$| $$  \\ $$| $$  \\ $$");
        System.out.println("   | $$   | $$_____/| $$  | $$| $$ | $$ | $$| $$  | $$      | $$  \\ $$ /$$__  $$| $$  | $$| $$_  $$       | $$  | $$| $$  | $$| $$  | $$");
        System.out.println("   | $$   |  $$$$$$$| $$  | $$| $$ | $$ | $$|  $$$$$$/      | $$$$$$$/|  $$$$$$$| $$  | $$| $$ \\  $$      | $$  | $$| $$$$$$$/| $$$$$$$/");
        System.out.println("   |__/    \\_______/|__/  |__/|__/ |__/ |__/ \\______/       |_______/  \\_______/|__/  |__/|__/  \\__/      |__/  |__/| $$____/ | $$____/");
        System.out.println("                                                                                                                    | $$      | $$      ");
        System.out.println("                                                                                                                    | $$      | $$      ");
        System.out.println("                                                                                                                    |__/      |__/      ");
        

    }





}