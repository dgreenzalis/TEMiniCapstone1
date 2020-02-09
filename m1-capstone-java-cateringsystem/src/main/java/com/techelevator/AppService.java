package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AppService {

	public AppService(String inventoryFile) {

		masterInventory = cateringInventory.generateInventory(inventoryFile);
	}

	private Wallet appServWallet = new Wallet();
	private Scanner appServiceScanner = new Scanner(System.in);
	private CateringInventory cateringInventory = new CateringInventory();
	private List<Product> masterInventory;
//	private List<Product> masterInventory = cateringInventory.generateInventory("cateringSystemJunitTestFile.txt");
	private OurMenu menu = new OurMenu();

	public void displayCateringItems() {
		System.out.println("");
		System.out.println("----------------------------------------------------");
		System.out.println("You chose to display catering items (please appreciate how hard this was to do): ");
		System.out.println("----------------------------------------------------");
		System.out.println("");
		for (Product item : masterInventory) {
			System.out.println(String.format("%-5s %-20s %-5s %-5s %-5s", item.getId(), item.getName(), item.getPrice(),
					item.getType(), item.getQuantityToDisplay()));
		}
		System.out.println("");
		System.out.println("----------------------------------------------------");
	}

	public void subMenuOptions() throws IOException {

		boolean exitSubMenu = false;
		while (exitSubMenu == false) {

			String subMenuInput = menu.printSubMenu(appServWallet);
			// Adding money
			if (subMenuInput.equals("1")) {
				System.out.println("You chose Add Money. How much?: ");
				String inputString = appServiceScanner.nextLine();
				appServWallet.addMoney(inputString);
				System.out.println("$" + appServWallet.getCurrentAccountBalance());
				exitSubMenu = false;
			}
			// Selecting Products
			else if (subMenuInput.equals("2")) {
				// TODO: add items to cart
				selectItems();
				exitSubMenu = false;
			} // complete transaction
			else if (subMenuInput.equals("3")) {
				appServWallet.checkOut();
				exitSubMenu = false;
			} // back to menu
			else if (subMenuInput.equals("4")) {
				exitSubMenu = true;
			} // wrong input
			else {
				System.out.println("WTF are you doing. Use the prompts idiot.");
			}
		}
	}

	private void selectItems() {
		boolean selectionExit = false;
		while (selectionExit == false) {
			displayCateringItems();
			try {
				System.out.println("Please select item ID or exit:");
				String userSelection = appServiceScanner.nextLine();
				//exit case
				if (userSelection.equalsIgnoreCase("exit")) {
					boolean yornLoop = false;
					while (yornLoop == false) {
						System.out.println("Would you like to clear your cart? Y or N");
						String clearYorN = appServiceScanner.nextLine();
						if (clearYorN.equalsIgnoreCase("Y")) {
							appServWallet.clearCart();
							yornLoop = true;
						} else if (clearYorN.equalsIgnoreCase("N")) {
							yornLoop = true;
						} else {
							System.out.println("What are you even doing?");
						}
					}
					break;
				}
				//verify product exists 
				int invalidEntryCounter = 0;
				for (Product p: masterInventory) {
					if (userSelection.equalsIgnoreCase(p.getId())) {
						break;
					}else{ 
						invalidEntryCounter++;
					}
				}
				if(invalidEntryCounter == masterInventory.size()) {
					System.out.println("What in tarnation! Pick from the list!");
					break;
				}
				
				System.out.println("How many would you like?");
				int selectionQuantity = Integer.parseInt(appServiceScanner.nextLine());
				if (selectionQuantity < 0) {
					System.out.println("You want negative items? Cute. Try again!");
					break;
				}
				
				for (Product p : masterInventory) {
					
					if (userSelection.equalsIgnoreCase(p.getId())) {
						System.out.println(p.getName() + " ADDED");
						// add to cart
						System.out.println("");
						System.out.println("Items in cart:");
						appServWallet.addToCart(p, selectionQuantity);
						appServWallet.getCartContents();
						
						break;
					} 
				}
				
			} catch (NumberFormatException ex) {
				System.out.println("Yooo!! My guy, use a number!");
			}
		}
	}

}
