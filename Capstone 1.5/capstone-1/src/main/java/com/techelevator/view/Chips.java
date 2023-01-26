package com.techelevator.view;

import com.techelevator.Snack;

public class Chips extends Snack {
    //Define Chips
    public Chips(String slotNumber, String prodName, double price) {
        super(slotNumber, prodName, price);
    }
//What Chips should Print
    @Override
    public String toString(){
        return "Crunch Crunch, Yum!";
    }
}
