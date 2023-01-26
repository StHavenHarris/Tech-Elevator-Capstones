package com.techelevator.view;

import com.techelevator.Snack;
//Set up inheritance for Snack
public class Gum extends Snack {

    //Define gum
    public Gum(String slotNumber, String prodName, double price) {
        super(slotNumber, prodName, price);
    }

    //What any type of gum should print out
    @Override
    public String toString(){
        return "Chew Chew, Yum!";
    }
}
