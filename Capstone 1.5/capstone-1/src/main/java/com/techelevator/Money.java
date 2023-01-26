package com.techelevator;

import java.io.IOException;
import java.util.Scanner;

public class Money {

    public static double getCurrentMoney;
    private static double currentMoney;

    public static double getCurrentMoney() {

        return currentMoney;
    }

    public static void setCurrentMoney(double currentMoney) {
        Money.currentMoney = currentMoney;
    }
    public void readMoney() {
        Scanner input = new Scanner(System.in);

        try {
                System.out.println("");
                System.out.println("There is currently " +currentMoney+ " dollars available." + " Please enter a whole dollar amount");
                String feedMoney = input.nextLine();
                Double moneyProvided = Double.parseDouble(feedMoney);

                boolean exitLoop = true;
                currentMoney += moneyProvided;
                while (exitLoop) {

                    System.out.println("You entered " + currentMoney + " dollars. Would you like to enter anything else? (Y/N)");
                    String exit = input.nextLine();


                    if (exit.equals("Y") || exit.equals("y")) {
                        System.out.print("Please enter a whole dollar amount");
                        String feedMoreMoney = input.nextLine();
                        Double moreMoneyProvided = Double.parseDouble(feedMoreMoney);
                        currentMoney += moreMoneyProvided;
                        System.out.println("");
                    } else if (exit.equals("N") || exit.equals("n")) {
                        System.out.println("The total amount entered is " + currentMoney + " dollars.");
                        exitLoop = false;
                        System.out.println("");
                    }
                    else if (!(exit.equals("Y") || exit.equals("N")) ){
                        System.out.println("The input you entered is invalid." );
                        exitLoop = false;
                        System.out.println("");
                    }

                }

        }catch (NumberFormatException e) {
            System.out.println(e);
        }
    }
    public static void moneyUpdater(){
        System.out.println( "Current Money Provided: " + currentMoney);
    }

}
