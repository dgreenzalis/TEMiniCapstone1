package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppService {
	
	public AppService(Customer customer) {
		this.appServCustomer = customer;
	}
	Customer appServCustomer;
	
	Scanner appServiceScanner = new Scanner (System.in);
	
	CateringInventory cateringInventory = new CateringInventory();

	Map<String, String[]> masterInventory = cateringInventory.generateInventory("cateringSystemJunitTestFile.txt");

	public void printMenu() {
		// System.out.println("Please work");
		System.out.println("");
		System.out.println("----------------------------------------------------");
		System.out.println("You chose to display catering items (please appreciate how hard this was to do): ");
		System.out.println("----------------------------------------------------");
		System.out.println("");
		
		for (String key : masterInventory.keySet()) {
			String[] values = masterInventory.get(key);
			System.out.println(values[0] + "   " + values[1]);
			// TODO: format all nice like
		}
		System.out.println("");
		System.out.println("----------------------------------------------------");
	}

	
	public boolean subMenuOptions(String subMenuInput) {
		//adding money
		boolean exitSubMenu = false;
		//Adding money
		if(subMenuInput.equals("1")) {
			appServCustomer.addMoney();
			System.out.println(appServCustomer.getCurrentAccountBalance());
			return exitSubMenu;
		}//Selecting Products
		else if (subMenuInput.equals("2")) {
			//TODO: select products
			
			return exitSubMenu;
		}//complete transaction
		else if (subMenuInput.equals("3")) {
			//TODO: Checkout
			
			
			return exitSubMenu;
		}//back to menu
		else if (subMenuInput.equals("4")) {
			return exitSubMenu = true;
		}//wrong input
		else {
			System.out.println("WTF are you doing. Use the prompts idiot.");
			return exitSubMenu;
		}
		
		
		
		
	}
}
