package com.techelevator.view;

import java.io.InputStream;
import java.io.OutputStream;

public class RemainingChange {

    public static void RemainingChange(double coinChange){



        //Quarters, Dimes, and Nickels
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        while (coinChange >= .25){
            quarters = quarters + 1;
            coinChange = coinChange - .25;
        }
        while (coinChange >= .10){
            nickels = dimes + 1;
            coinChange = coinChange - .10;
        }
        while (coinChange >= .5){
            nickels = nickels + 1;
            coinChange = coinChange - .5;
        }


        System.out.println("Please accept your change in the amount of: " + quarters +  " quarters "
                + dimes + " dimes " + nickels + " nickels ");
    }
}
