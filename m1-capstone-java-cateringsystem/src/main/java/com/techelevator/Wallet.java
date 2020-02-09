package com.techelevator;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Wallet {

	public Map<Product, Integer> cartMap = new HashMap<Product, Integer>();
	private Scanner customerScanner = new Scanner(System.in);
	private float currentAccountBalance = 0.00f;
	private AuditLog appServAuditLog = new AuditLog();

	//redundant method to pull Account Balance (makes usable outside this class? we think? not sure?)
	public float getCurrentAccountBalance() {
		return currentAccountBalance;
	}
	
	//method to add money to account. called in AppService. returns current account balance
	public float addMoney(String inputString) throws IOException {

		try {
			int inputMoney = Integer.parseInt(inputString);
			//if number is negative or over 5000
			if (inputMoney < 0) {
				System.out.println("Can't go negative dawg.");
			} else if (currentAccountBalance + inputMoney > 5000) {
				System.out.println("Yo, dawg wtf are you doing. thats so much money. chill.");
			} else {
				currentAccountBalance += inputMoney;
				//Adds entry to log file for ADDING MONEY
				appServAuditLog.logAddMoney(inputMoney, currentAccountBalance);
			}
		//catch block for if input is non-integer
		} catch (NumberFormatException ex) {
			System.out.println("Those are some mighty weird numbers you're using, try again!");
		}
		return currentAccountBalance;
	}
	
	// adds product referenced from Inventory list to cartMap if sufficient quantity in stock (could have sep into a Cart Class?)
	public void addToCart(Product productToAdd, int amount) {

		if (amount < 0) {
			System.out.println("You want negative items? Cute. Try again!");
		} else if ((amount) > productToAdd.getQuantity()) {
			System.out.println("Insufficient stock!");
		} else if (cartMap.containsKey(productToAdd)) {
			productToAdd.subtractFromCurrentQuantity(amount);
			cartMap.put(productToAdd, amount);
		} else {
			cartMap.put(productToAdd, new Integer(amount));
			productToAdd.subtractFromCurrentQuantity(amount);
		}
	}
	
	//empties cartMap's Keys + Values (could have sep into a Cart Class?)
	public void clearCart() {
		for (Product p : cartMap.keySet()) {
			p.setQuantity(50);
		}
		cartMap.clear();
	}
	
	//references Account Bal vs Cart Total. If sufficient funds, initiate check out. Generates receipt, "gives change", resets Account Balance, clears cart.
	public void checkOut() {
		if (currentAccountBalance < getCartTotalDollarAmount()) {
			System.out.println("lol sike, broke turd (add more money or subtract from cart");
		} else {
			float changeReturned = currentAccountBalance - getCartTotalDollarAmount();
			float tempCurrentAccountBal = currentAccountBalance;
			for (Product p : cartMap.keySet()) {
				String pType = "";
				if (p.getType().contentEquals("E")) {
					pType = "Entree";
				} else if (p.getType().contentEquals("B")) {
					pType = "Beverage";
				} else if (p.getType().contentEquals("A")) {
					pType = "Appetizer";
				} else if (p.getType().contentEquals("D")) {
					pType = "Dessert";
				}
				System.out.println(String.format("%-5s %-5s %-15s %-5s %-5s", cartMap.get(p), pType, p.getName(),
						"$" + p.getPrice(), "$" + (String.format("%.2f", (p.getPrice() * cartMap.get(p))))));

				tempCurrentAccountBal = tempCurrentAccountBal - (p.getPrice() * cartMap.get(p));
				//create entry in log file for each item bought in current checkout
				appServAuditLog.logItemPurchased(cartMap.get(p), p.getName(), p.getId(),
						(p.getPrice() * cartMap.get(p)), tempCurrentAccountBal);
			}
			
			currentAccountBalance = 0;
			appServAuditLog.logGivingChange(changeReturned, currentAccountBalance);
			System.out.println("$" + String.format("%.2f", getCartTotalDollarAmount()));
			makeCorrectChange(changeReturned);
			System.out.println("Your change for this transaction is: $" + (String.format("%.2f", changeReturned)));
			clearCart();
		}

	}
	
	//Generates report of info for current contents in cartMap (could have sep into a Cart Class?)
	public void getCartContents() {
//		System.out.println(cartMap.size() + " different item(s) in cart");
		for (Product p : cartMap.keySet()) {
			System.out.println("");
			System.out.println(p.getName() + ": " + cartMap.get(p));
//			System.out.println(p.getQuantity() + " " + p.getName() + "(s) left in stock");
		}
		System.out.println("");
		System.out.println("Cart Total: $" + String.format("%.2f", getCartTotalDollarAmount()));
	}

	//calculates cart total dollar amount (could have sep into a Cart Class?)
	public float getCartTotalDollarAmount() {
		float currentCartTotal = 0.00f;
		for (Product p : cartMap.keySet()) {
			currentCartTotal = currentCartTotal + (p.getPrice() * (float) cartMap.get(p));
		}
		return currentCartTotal;
	}

	//breaks down returned change value into smallest amount of physical currency.(She's a BIG guuurl)
	private void makeCorrectChange(float changeValue) {
		String moneyString = Float.toString(changeValue);
		String[] moneyStringArray = moneyString.split("\\.");
		Integer wholeDollarsInteger = Integer.parseInt(moneyStringArray[0]);
		int wholeDollars = wholeDollarsInteger.intValue();
		Integer centsInteger;
		if (moneyStringArray[1].length() == 2) {
			centsInteger = Integer.parseInt(moneyStringArray[1]);
		} else if (moneyStringArray[1].length() == 1) {
			moneyStringArray[1] = moneyStringArray[1].concat("0");
			centsInteger = Integer.parseInt(moneyStringArray[1]);
		} else if (moneyStringArray[1].length() == 0) {
			moneyStringArray[1] = moneyStringArray[1].concat("00");
			centsInteger = Integer.parseInt(moneyStringArray[1]);
		} else {
			centsInteger = Integer.parseInt(moneyStringArray[1].substring(0, 2));
		}
		int cents = centsInteger.intValue();
		int twenties;
		int tens;
		int fives;
		int ones;
		twenties = wholeDollars / 20;
		wholeDollars = wholeDollars - (twenties * 20);
		tens = wholeDollars / 10;
		wholeDollars = wholeDollars - (tens * 10);
		fives = wholeDollars / 5;
		wholeDollars = wholeDollars - (fives * 5);
		ones = wholeDollars / 1;
		wholeDollars = wholeDollars - (ones * 1);
		int quarters;
		int dimes;
		int nickels;
		int pennies;
		quarters = cents / 25;
		cents = cents - (quarters * 25);
		dimes = cents / 10;
		cents = cents - (dimes * 10);
		nickels = cents / 5;
		cents = cents - (nickels * 5);
		pennies = cents / 1;
		cents = cents - (pennies * 1);
		System.out.println(String.format("%-13s | %-13s | %-13s | %-13s", "Twenties: " + twenties, "Tens: " + tens,
				"Fives: " + fives, "Ones: " + ones));
		System.out.println(String.format("%-13s | %-13s | %-13s | %-13s", "Quarters: " + quarters, "Dimes: " + dimes,
				"Nickels: " + nickels, "Pennies: " + pennies));

	}

}