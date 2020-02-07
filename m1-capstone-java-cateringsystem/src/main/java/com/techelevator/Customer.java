package com.techelevator;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer {
	
	List<Product> cartList = new ArrayList<Product>();

	Scanner customerScanner = new Scanner(System.in);
	
	public Double currentAccountBalance = 0.00;

	public Double getCurrentAccountBalance() {
		return currentAccountBalance;
	}
	
	public Double addMoney() {
		//TODO: limit input to $5000
		
		System.out.println("You chose Add Money. How much?: ");
		int inputMoney = Integer.parseInt(customerScanner.nextLine());
		
		currentAccountBalance += inputMoney;
		return currentAccountBalance;
	//TODO: subtract checkout total from balance
		//TODO: make sure balance is greater than checkout total
	}
	
	public List<Product> addToCart(String productId){
		
		
		return cartList;
	}
	
}
