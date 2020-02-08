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

	// CONSTRUCTOR

	public float getCurrentAccountBalance() {
		return currentAccountBalance;
	}

	public float addMoney() throws IOException {
		// TODO: limit input to $5000

		System.out.println("You chose Add Money. How much?: ");
		int inputMoney = Integer.parseInt(customerScanner.nextLine());

		if (currentAccountBalance + inputMoney > 5000) {
			System.out.println("Yo, dawg wtf are you doing. thats so much money. chill.");
		} else {
			currentAccountBalance += inputMoney;
			appServAuditLog.logAddMoney(inputMoney, currentAccountBalance);
		}

		return currentAccountBalance;
		// TODO: subtract checkout total from balance
		// TODO: make sure balance is greater than checkout total
	}

	public void addToCart(Product productToAdd, int amount) {
		if (cartMap.containsKey(productToAdd) && (amount) > productToAdd.getQuantity()) {
			System.out.println("Insufficient stock!");
		} else if (cartMap.containsKey(productToAdd)) {
//			
			productToAdd.subtractFromCurrentQuantity(amount);
			amount = amount;
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

}