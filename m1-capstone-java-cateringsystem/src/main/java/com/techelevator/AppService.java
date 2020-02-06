package com.techelevator;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AppService {
	
	public AppService(Customer customer) {
		this.customer = customer;
	}
	Customer customer;
	
	Scanner appServiceScanner = new Scanner (System.in);
	
	CateringInventory cateringInventory = new CateringInventory();

	Map<String, String[]> masterInventory = cateringInventory.generateInventory("cateringSystemJunitTestFile.txt");

	public void printMenu() {
		// System.out.println("Please work");
		for (String key : masterInventory.keySet()) {
			String[] values = masterInventory.get(key);
			System.out.println(values[0] + "   " + values[1]);
			// TODO: format all nice like

		}

	}

	public void subMenuOptions(String subMenuInput) {
		if(subMenuInput.equals("1")) {
			System.out.println("You chose Add Money. How much, bitch?:");
			int addAmount = Integer.parseInt(appServiceScanner.nextLine());
			customer.addMoney(addAmount);
			System.out.println(customer.getCurrentAccountBalance());
		}
	}
}
