package com.techelevator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Wallet {

	//	private List<Product> cartList = new ArrayList<Product>();
	public Map<Product, Integer> cartMap = new HashMap<Product, Integer>();
	private Scanner customerScanner = new Scanner(System.in);
	private float currentAccountBalance = 0.00f;
	
	//CONSTRUCTOR
	
	public float getCurrentAccountBalance() {
		return currentAccountBalance;
	}

	public float addMoney() {
		// TODO: limit input to $5000

		System.out.println("You chose Add Money. How much?: ");
		int inputMoney = Integer.parseInt(customerScanner.nextLine());

		if(currentAccountBalance + inputMoney >5000) {
			System.out.println("Yo, dawg wtf are you doing. thats so much money. chill.");
		}else {
			currentAccountBalance += inputMoney;
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
			amount =  amount;
			cartMap.put(productToAdd, amount);
		} else {
			cartMap.put(productToAdd, new Integer(amount));
			productToAdd.subtractFromCurrentQuantity(amount);
		}

	}
	public void removeFromCart() {

		
		
	}

	public void clearCart() {
		for (Product p : cartMap.keySet()){
			p.setQuantity(50);
		}
		cartMap.clear();
	}
	
	
	public void checkOut() {

		
		
		
		
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
			currentCartTotal = currentCartTotal + (p.getPrice() * (float)cartMap.get(p));
		}
		return currentCartTotal;
	}
	
	
		//NOT IN USE CURRENTLY
	/*
	private void getCartProductQuantity() {

		for (Product p : cartList) {
			String productName = p.getName();
			int quantityInCart = 1;

			for (Product i : cartList) {
				if (p.getId().equalsIgnoreCase(i.getId())) {
					quantityInCart++;
				}
			}
		}
	}
	*/
	
	
	
	
	
	
	
}