package com.techelevator;

import com.techelevator.view.Menu;
import com.techelevator.view.RemainingChange;
import com.techelevator.view.SnackInventory;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;


public class VendingMachineCLI {
//Constants
	private static final String MAIN_MENU_OPTION_DISPLAY_ITEMS = "Display Vending Machine Items";
	private static final String MAIN_MENU_OPTION_PURCHASE = "Purchase";
	private static final String MAIN_MENU_OPTION_EXIT = "Exit";
	private static final String[] MAIN_MENU_OPTIONS = { MAIN_MENU_OPTION_DISPLAY_ITEMS, MAIN_MENU_OPTION_PURCHASE, MAIN_MENU_OPTION_EXIT };

	private static final String PURCHASE_MENU_OPTION_FEED_MONEY = "Feed Money";
	private static final String PURCHASE_MENU_OPTION_SELECT_PRODUCT = "Select Product";
	private static final String PURCHASE_MENU_OPTION_FINISH_TRANSACTION = "Finish Transaction";
	private static final String CURRENT_MONEY_PROVIDED = "Current Money Provided";
	private static final String[] PURCHASE_MENU_OPTIONS = { PURCHASE_MENU_OPTION_FEED_MONEY, PURCHASE_MENU_OPTION_SELECT_PRODUCT, PURCHASE_MENU_OPTION_FINISH_TRANSACTION};
//Variables
	private Menu menu;
	final File vendFile =  new File ("vendingmachine.csv");


	SnackInventory snackMenu = new SnackInventory(vendFile);
	Money feedMoney = new Money();
	File vendLogFile = new File("Log.txt");
	DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
	LocalDateTime currTime = LocalDateTime.now();



	double totalMoney = 0;
	double moneyLeft = 0;
	private Map<String,SnackInventory> snackList = new HashMap<>();
	Set<String> keys = snackList.keySet();
	private Scanner snackID;


	public VendingMachineCLI(Menu menu) throws IOException {
		this.menu = menu;
	}
//Begin Machine
	public void run() {
		boolean display = true;


		while (display) {
			String choice = (String) menu.getChoiceFromOptions(MAIN_MENU_OPTIONS);

			if (choice.equals(MAIN_MENU_OPTION_DISPLAY_ITEMS)) {
				// display vending machine items


				snackMenu.displayInventory();

				System.out.println();


			} else if (choice.equals(MAIN_MENU_OPTION_PURCHASE)) {

				while (display) {
					//Update Money

					Money.moneyUpdater();
					String choice2 = (String) menu.getChoiceFromOptions(PURCHASE_MENU_OPTIONS);


					if (choice2.equals(PURCHASE_MENU_OPTION_FEED_MONEY)) {
						//Given Machine money
						feedMoney.readMoney();
						//totalMoney = totalMoney + currentMoney(what user entered for money)
						totalMoney = totalMoney + Money.getCurrentMoney();
						//For change and logs if user cancels purchase
						moneyLeft = totalMoney;
						//Log1 show time and how much money was given
						try (PrintWriter log1 = new PrintWriter(new FileWriter(vendLogFile, true))){
							log1.println(dtf.format(currTime) + " FEED Money: " + totalMoney );

						}
						catch (IOException ex){}


					} else if (choice2.equals(PURCHASE_MENU_OPTION_SELECT_PRODUCT)) {
						// select product menu
						System.out.println("Please look through all options (scroll to bottom)");
						System.out.println();
						snackMenu.displayInventory();

						//Get User input
						snackID = new Scanner(System.in);
						System.out.println("Please enter item ID (ex. A1): ");
						String snackId = snackID.nextLine();
						double totalCash = 0;
						String[] allTheStuffINeed = new String[3];

						//Check if user input is correct and has enough money
						for (Snack menuSnack : snackMenu.showInventory()) {
							if (snackId.contentEquals(menuSnack.getSlotNumber())) {
								totalCash = totalMoney - menuSnack.getPrice();

								allTheStuffINeed[0] = Double.toString(totalCash);
								allTheStuffINeed[1] = menuSnack.getSlotNumber();
								allTheStuffINeed[2] = menuSnack.getProdName();

								//Check if item is sold out
								if (menuSnack.getProductAmmount() == 0) {
									System.out.println("SOLD OUT!!");
								} else if (totalMoney >= menuSnack.getPrice()) {

									//Give user their product
									menuSnack.setProductAmmount(menuSnack.getProductAmmount() - 1);
									System.out.println("Here is your " + menuSnack.getProdName() + ". " + "The cost was: $" + menuSnack.getPrice() +
											". " + "Money remaining: $" + totalCash + ". " + menuSnack.toString());

									//update current money
									Money.setCurrentMoney(totalCash);
									//log transaction
									try (PrintWriter log2 = new PrintWriter(new FileWriter(vendLogFile, true))){
										log2.println(dtf.format(currTime) +" " + menuSnack.getProdName() + " " + menuSnack.getPrice());
									}catch (IOException ex){}


								} else if (totalCash <= 0) {

									System.out.println("You do not have enough money for " + menuSnack.getProdName() + ". Please insert more money.");
								} else {
									System.out.println("Could not find that snack ID.");
								}

							}



						}
						//Used for change in option 3
						moneyLeft = totalCash;





					} else if (choice2.equals(PURCHASE_MENU_OPTION_FINISH_TRANSACTION)){
						// purchase items selected and give change
						RemainingChange rChange = new RemainingChange();
						System.out.println();
						System.out.println("Your change is: " + moneyLeft);
						rChange.RemainingChange(moneyLeft);
						moneyLeft = 0;
						Money.setCurrentMoney(moneyLeft);
						//Log the change and end of Transaction
						try (PrintWriter log3 = new PrintWriter(new FileWriter(vendLogFile, true))){
							log3.println(dtf.format(currTime) + " GIVE CHANGE: " + moneyLeft + " " + "$0.00");
						} catch (IOException e) {
							throw new RuntimeException(e);
						}
						run();

					}

				}

			}
			//Restart Machine

			 else if (choice.equals(MAIN_MENU_OPTION_EXIT)){
				display = false;
				System.out.println("Transaction done.");
			}
			else {
				System.out.println("Please enter 1, 2, or 3");
				System.out.println();
			}
		}
	}





	public static void main(String[] args) throws IOException {
		Menu menu = new Menu(System.in, System.out);
		VendingMachineCLI cli = new VendingMachineCLI(menu);
		cli.run();
	}
}
