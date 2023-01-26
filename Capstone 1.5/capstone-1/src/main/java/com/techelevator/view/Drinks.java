package com.techelevator.view;

import com.techelevator.Snack;

public class Drinks extends Snack {
    //Define Drink
    public Drinks(String slotNumber, String prodName, double price) {
        super(slotNumber, prodName, price);
    }
    //What Drinks should print out
    @Override
    public String toString(){
        return "Glug Glug, Yum!";
    }
}
