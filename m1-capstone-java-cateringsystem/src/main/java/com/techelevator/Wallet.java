package com.techelevator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Wallet {

	// private List<Product> cartList = new ArrayList<Product>();
	public Map<Product, Integer> cartMap = new HashMap<Product, Integer>();
	private Scanner customerScanner = new Scanner(System.in);
	private float currentAccountBalance = 0.00f;
	private AuditLog appServAuditLog = new AuditLog();

	public float getCurrentAccountBalance() {
		return currentAccountBalance;
	}

	public float addMoney() throws IOException {

		System.out.println("You chose Add Money. How much?: ");
		String inputString = customerScanner.nextLine();

		try {
			int inputMoney = Integer.parseInt(inputString);
			if(inputMoney < 0) {
				System.out.println("Can't go negative dawg.");
				
			}else if (currentAccountBalance + inputMoney > 5000) {
				System.out.println("Yo, dawg wtf are you doing. thats so much money. chill.");
			} else {
				currentAccountBalance += inputMoney;
				appServAuditLog.logAddMoney(inputMoney, currentAccountBalance);
			}
		}
		catch (NumberFormatException ex) {
			System.out.println("Those are some mighty weird numbers you're using, try again!");
		}
		return currentAccountBalance;
	}

	public void addToCart(Product productToAdd, int amount) {

		if ((amount) > productToAdd.getQuantity()) {
			System.out.println("Insufficient stock!");
		} else if (cartMap.containsKey(productToAdd)) {
//			
			productToAdd.subtractFromCurrentQuantity(amount);
			cartMap.put(productToAdd, amount);
		} else {
			cartMap.put(productToAdd, new Integer(amount));
			productToAdd.subtractFromCurrentQuantity(amount);
		}

	}

	public void removeFromCart() {

	}

	public void clearCart() {
		for (Product p : cartMap.keySet()) {
			p.setQuantity(50);
		}
		cartMap.clear();
	}

	public void checkOut() {
		if (currentAccountBalance < getCartTotalDollarAmount()) {
			System.out.println("lol sike, broke turd (add more money or subtract from cart");
		} else {
			float changeReturned = currentAccountBalance - getCartTotalDollarAmount();

			float tempCurrentAccountBal = currentAccountBalance;

			for (Product p : cartMap.keySet()) {
				System.out.println(String.format("%-5s %-5s %-15s %-5s %-5s", cartMap.get(p), p.getType(), p.getName(),
						p.getPrice(), (p.getPrice() * cartMap.get(p))));
				tempCurrentAccountBal = tempCurrentAccountBal - (p.getPrice() * cartMap.get(p));
				appServAuditLog.logItemPurchased(cartMap.get(p), p.getName(), p.getId(),
						(p.getPrice() * cartMap.get(p)), tempCurrentAccountBal);
			}

			currentAccountBalance = 0;
			appServAuditLog.logGivingChange(changeReturned, currentAccountBalance);
			System.out.println(getCartTotalDollarAmount());
			makeCorrectChange(changeReturned);
			System.out.println("Your change for this transaction is: " + changeReturned);
		}

	}

	public void getCartContents() {
		System.out.println(cartMap.size() + " different item(s) in cart");
		for (Product p : cartMap.keySet()) {
			System.out.println(p.getName() + " --- " + cartMap.get(p));
			System.out.println(p.getQuantity() + " " + p.getName() + "(s) left in stock");
		}
		System.out.println(getCartTotalDollarAmount());

	}

	private float getCartTotalDollarAmount() {
		float currentCartTotal = 0.00f;
		for (Product p : cartMap.keySet()) {
			currentCartTotal = currentCartTotal + (p.getPrice() * (float) cartMap.get(p));
		}
		return currentCartTotal;
	}

	private void makeCorrectChange(float changeValue) {
		String moneyString = Float.toString(changeValue);
		String[] moneyStringArray = moneyString.split("\\.");
		Integer wholeDollarsInteger = Integer.parseInt(moneyStringArray[0]);
		int wholeDollars = wholeDollarsInteger.intValue();
		Integer centsInteger = Integer.parseInt(moneyStringArray[1]);
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