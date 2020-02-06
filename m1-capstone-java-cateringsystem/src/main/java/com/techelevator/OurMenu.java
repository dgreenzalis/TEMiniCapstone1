package com.techelevator;

import java.util.Scanner;

public class OurMenu {
	
	private Scanner inputScanner = new Scanner(System.in);
//	Customer customer = new Customer();
	
	public String printMainMenu() {
		System.out.println("(1) Display Catering Items");
		System.out.println("(2) Order");
		System.out.println("(3) Quit");
		
		return inputScanner.nextLine();
	}
	
	public String printSubMenu(Customer addCustomer) {
		//TODO: use super for this constructor?
		
		System.out.println("(1) Add Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Transaction");
		System.out.println("(4) Back to Main Menu");
		System.out.println("Current Balance: $" + addCustomer.getCurrentAccountBalance()); //TODO - Insert account balance later.
		
		return inputScanner.nextLine();
	}

}
