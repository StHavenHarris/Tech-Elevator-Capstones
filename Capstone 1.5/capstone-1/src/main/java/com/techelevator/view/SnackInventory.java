package com.techelevator.view;
import com.techelevator.Snack;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class SnackInventory {
    //Set csv file
  private   File vendFile =  new File ("vendingmachine.csv");


//Array of snacks
    private List<Snack> allSnacks = new ArrayList<>();

    //Constructor
    public SnackInventory(File vendFile) {
        this.vendFile = vendFile;
        try {
            this.snackStock();
        } catch (FileNotFoundException e) {

        }

    }

    //Sort snacks by location, price, type
    public void snackStock() throws FileNotFoundException {
        try(Scanner fileScanner = new Scanner(vendFile)) {

            while(fileScanner.hasNextLine()) {

                String line = fileScanner.nextLine();
                String [] sortedSnacks = line.split("\\|");

                if (sortedSnacks[3].contentEquals("Chip")) {
                    Snack a = new Chips (sortedSnacks[0], sortedSnacks[1], Double.parseDouble(sortedSnacks[2]));
                    allSnacks.add(a);

                }else if (sortedSnacks[3].contentEquals("Candy")) {
                    Snack a = new Candy (sortedSnacks[0], sortedSnacks[1], Double.parseDouble(sortedSnacks[2]));
                    allSnacks.add(a);

                }else if (sortedSnacks[3].contentEquals("Drink")) {
                    Snack a = new Drinks (sortedSnacks[0], sortedSnacks[1], Double.parseDouble(sortedSnacks[2]));
                    allSnacks.add(a);

                }else if (sortedSnacks[3].contentEquals("Gum")) {
                    Snack a = new Gum (sortedSnacks[0], sortedSnacks[1], Double.parseDouble(sortedSnacks[2]));
                    allSnacks.add(a);

                }else {
                    System.out.println(sortedSnacks[3]);
                }

            }

        }

    }

    //Show snacks
    public List<Snack> showInventory() {

        return allSnacks;
    }
//Display Inventory menu
    public void displayInventory(){
        for (Snack snack : allSnacks) {
            System.out.print(snack.getSlotNumber()+ " | ");
            System.out.print(snack.getProdName() + " | ");
            System.out.print(snack.getPrice() + " | ");
            System.out.print(snack.getProductAmmount() + "\n");
    }


    }
}
