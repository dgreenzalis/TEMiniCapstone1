package com.techelevator;

import java.io.File;
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
	// load file in constructor
	private List<Product> masterInventory;
//	private List<Product> masterInventory = cateringInventory.generateInventory("cateringSystemJunitTestFile.txt");
	private OurMenu menu = new OurMenu();

	public void displayCateringItems() {
		// System.out.println("Please work");
		System.out.println("");
		System.out.println("----------------------------------------------------");
		System.out.println("You chose to display catering items (please appreciate how hard this was to do): ");
		System.out.println("----------------------------------------------------");
		System.out.println("");
		for (Product item : masterInventory) {
			System.out.println(String.format("%-5s %-20s %-5s %-5s %-5s", item.getId(), item.getName(), item.getPrice(),
					item.getType(), item.getQuantityToDisplay()));
			// TODO: format all nice like
		}
		System.out.println("");
		System.out.println("----------------------------------------------------");
	}

	public void subMenuOptions() {

		boolean exitSubMenu = false;
		while (exitSubMenu == false) {

			String subMenuInput = menu.printSubMenu(appServWallet);
			// Adding money
			if (subMenuInput.equals("1")) {
				appServWallet.addMoney();
				System.out.println(appServWallet.getCurrentAccountBalance());
				exitSubMenu = false;
			}

			// Selecting Products
			else if (subMenuInput.equals("2")) {
				// TODO: add items to cart
				selectItems();
				exitSubMenu = false;
			} // complete transaction
			else if (subMenuInput.equals("3")) {
				// TODO: Checkout
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
			boolean wrongInputConfirmation = true;
			
			displayCateringItems();
			System.out.println("Please select item ID or exit:");
			String userSelection = appServiceScanner.nextLine();
			
			if (userSelection.equalsIgnoreCase("exit")) {
				appServWallet.clearCart();
				break;
			}
			System.out.println("How many would you like?");
			int selectionQuantity = Integer.parseInt(appServiceScanner.nextLine());

			for (Product p : masterInventory) {

				if (userSelection.equals(p.getId())) {
					System.out.println(p.getName() + " ADDED");
					// add to cart
					appServWallet.addToCart(p, selectionQuantity);
					appServWallet.getCartContents();
					wrongInputConfirmation = false;
					break;
				}
			}
						if (wrongInputConfirmation == true) {
				System.out.println("Try again idiot!");
			}
		}
	}

}
