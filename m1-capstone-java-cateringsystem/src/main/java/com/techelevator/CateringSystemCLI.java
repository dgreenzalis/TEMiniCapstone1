package com.techelevator;

import java.util.Scanner;

import com.techelevator.view.Menu;

public class CateringSystemCLI {
	
	//Main Menu Options
	private final static String Display_Catering_Items = "1";
	private final static String Order = "2";
	private final static String Quit= "3";
	
	//Sub-Menu Options
	private final static String Add_Money = "1";
	private final static String Select_Products = "2";
	private final static String Complete_Transaction= "3";
	
	
	private Wallet mainWallet = new Wallet();
	
	private OurMenu menu;
	private AppService appService = new AppService("cateringsystem.csv");
	
	
	public CateringSystemCLI(OurMenu menu) {
		this.menu = menu;
	}
	
	public static void main(String[] args) {
		OurMenu menu = new OurMenu();
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();
	}	

	public void run() {
		
		while (true) {
		
			String choice = menu.printMainMenu();
			
			if (choice.equals(Display_Catering_Items)) {
				appService.displayCateringItems();
			}
			else if (choice.equals(Order)) {
				
					appService.subMenuOptions();
			}
			else if (choice.equals(Quit)) {
				System.exit(0);
			}
			else {
				System.out.println("Invalid entry. Please re-enter.");
			}

		}
	}


}
