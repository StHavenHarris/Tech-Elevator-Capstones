package com.techelevator.view;

import com.techelevator.Snack;

public class Candy extends Snack {
    //Defne candy
    public Candy(String slotNumber, String prodName, double price) {
        super(slotNumber, prodName, price);
    }

    //What Candy should print
    @Override
    public String toString(){
        return "Munch Munch, Yum!";
    }
}
