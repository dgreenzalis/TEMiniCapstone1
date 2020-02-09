package com.techelevator;

import java.util.Scanner;

public class OurMenu {
	
	private Scanner inputScanner = new Scanner(System.in);
	
	//prints out our menus
	public String printMainMenu() {
		System.out.println("(1) Display Catering Items");
		System.out.println("(2) Order");
		System.out.println("(3) Quit");
		
		return inputScanner.nextLine();
	}
	
	public String printSubMenu(Wallet wallet) {
		
		System.out.println("");
		System.out.println("----------------------------------------------------");
		System.out.println("You chose to Order items (I'm slowly dying inside): ");
		System.out.println("----------------------------------------------------");
		System.out.println("");
		
		System.out.println("(1) Add Money");
		System.out.println("(2) Select Products");
		System.out.println("(3) Complete Transaction");
		System.out.println("(4) Back to Main Menu");
		System.out.println("Current Balance: $" + wallet.getCurrentAccountBalance()); //TODO - Insert account balance later.
		
		System.out.println("");
		System.out.println("----------------------------------------------------");
		return inputScanner.nextLine();
	}

}
