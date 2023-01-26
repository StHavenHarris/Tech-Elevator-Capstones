package com.techelevator;

public class Snack {
    private String slotNumber;
    private String prodName;
    private double price;
    private int productAmmount = 5;

//Constructor
    public Snack(String slotNumber, String prodName, double price){
        this.slotNumber = slotNumber;
        this.prodName = prodName;
        this.price = price;
    }

    //Getters and Setters

    public String getSlotNumber() {
        return slotNumber;
    }

    public String getProdName(){
        return prodName;
    }

    public double getPrice(){
        return price;
    }

    public int getProductAmmount() {
        return productAmmount;
    }

    public void setProductAmmount(int productAmmount) {
        this.productAmmount = productAmmount;
    }
}
