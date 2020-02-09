package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AppService {

	public AppService(String inventoryFile) {
		//generate masterInventory for displaying menu from inventory file via constructor
		masterInventory = cateringInventory.generateInventory(inventoryFile);
	}

	private Wallet appServWallet = new Wallet();
	private Scanner appServiceScanner = new Scanner(System.in);
	private CateringInventory cateringInventory = new CateringInventory();
	private List<Product> masterInventory;
	private OurMenu menu = new OurMenu();
	
	//printing menu for use in "Display Menu" and "Select Items"
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
	
	//Logic for choosing Sub Menu options
	public void subMenuOptions() throws IOException {

		boolean exitSubMenu = false;
		while (exitSubMenu == false) {

			String subMenuInput = menu.printSubMenu(appServWallet);
			// Adding money
			if (subMenuInput.equals("1")) {
				System.out.println("You chose Add Money. How much? (whole dollars please!): ");
				String inputString = appServiceScanner.nextLine();
				appServWallet.addMoney(inputString);
				System.out.println("$" + appServWallet.getCurrentAccountBalance());
				exitSubMenu = false;
			}
			else if (subMenuInput.equals("2")) {
				selectItems();
				exitSubMenu = false;
			} 
			else if (subMenuInput.equals("3")) {
				appServWallet.checkOut();
				exitSubMenu = false;
			} 
			else if (subMenuInput.equals("4")) {
				exitSubMenu = true;
			} 
			else {
				System.out.println("WTF are you doing. Use the prompts idiot.");
			}
		}
	}
	
	//logic for selecting items (Sub Menu 2) (potentially refactor into Wallet or new Cart Class?)
	private void selectItems() {
		boolean selectionExit = false;
		while (selectionExit == false) {
			displayCateringItems();
			try {
				System.out.println("Please select item ID or exit:");
				String userSelection = appServiceScanner.nextLine();
				//choosing to exit selection menu (W/ option to clear cart)
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
				//Verify product exists 
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
				//quantity prompt + negative number safeguard
				System.out.println("How many would you like?");
				int selectionQuantity = Integer.parseInt(appServiceScanner.nextLine());
				if (selectionQuantity < 0) {
					System.out.println("You want negative items? Cute. Try again!");
					break;
				}
				//Add Items to cart
				for (Product p : masterInventory) {
					if (userSelection.equalsIgnoreCase(p.getId())) {
//						System.out.println(p.getName() + " ADDED");
						System.out.println("");
						appServWallet.addToCart(p, selectionQuantity);
						System.out.println("Items in cart:");
						appServWallet.getCartContents();
						break;
					} 
				}
			//String for item quantity safeguard 
			} catch (NumberFormatException ex) {
				System.out.println("Yooo!! My guy, use a number!");
			}
		}
	}
}
