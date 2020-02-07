package com.techelevator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AppService {

	public AppService() {
	}

	public AppService(Customer customer) {
		this.appServCustomer = customer;
	}

	Customer appServCustomer;

	Scanner appServiceScanner = new Scanner(System.in);

	CateringInventory cateringInventory = new CateringInventory();

	List<Product> masterInventory = cateringInventory.generateInventory("cateringSystemJunitTestFile.txt");

	public void displayCateringItems() {
		// System.out.println("Please work");
		System.out.println("");
		System.out.println("----------------------------------------------------");
		System.out.println("You chose to display catering items (please appreciate how hard this was to do): ");
		System.out.println("----------------------------------------------------");
		System.out.println("");
		for (Product item : masterInventory) {
			System.out.println(
					item.getId() + "   " + item.getName() + "   $" + item.getPrice() + "    " + item.getType());
			// TODO: format all nice like
		}
		System.out.println("");
		System.out.println("----------------------------------------------------");
	}

	public boolean subMenuOptions(String subMenuInput) {
		// adding money
		boolean exitSubMenu = false;
		// Adding money
		if (subMenuInput.equals("1")) {
			appServCustomer.addMoney();
			System.out.println(appServCustomer.getCurrentAccountBalance());
			return exitSubMenu;
		}

		// Selecting Products
		else if (subMenuInput.equals("2")) {
			// TODO: select products
			boolean backToMainMenu = false;
			boolean selectionExit = false;
			while (backToMainMenu == false)
			while (selectionExit == false) {
				displayCateringItems();
				System.out.println("Please select item ID or exit:");
				String userSelection = appServiceScanner.nextLine();
				for (Product p : masterInventory) {
					if (userSelection.equals(p.getId())) {
						System.out.println(p.getName() + " ADDED");
						selectionExit = true;
					}else if (userSelection.equals("exit")) {
						backToMainMenu = true;
					}else {
						System.out.println("Try again idiot!");
					}
					
				}
			}

			return exitSubMenu;
		} // complete transaction
		else if (subMenuInput.equals("3")) {
			// TODO: Checkout

			return exitSubMenu;
		} // back to menu
		else if (subMenuInput.equals("4")) {
			return exitSubMenu = true;
		} // wrong input
		else {
			System.out.println("WTF are you doing. Use the prompts idiot.");
			return exitSubMenu;
		}

	}
}
