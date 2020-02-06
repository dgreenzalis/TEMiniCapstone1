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
	
	
	private Customer dude = new Customer();
	
	private OurMenu menu;
	private AppService appService = new AppService(dude);
	
	
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
				
				appService.printMenu();
				

			}
			else if (choice.equals(Order)) {
				//TODO - Load subMenu
				while (appService.subMenuOptions(menu.printSubMenu(dude)) == false ) {
					
				System.out.println("");
				System.out.println("----------------------------------------------------");
				System.out.println("You chose to Order items (I'm slowly dying inside): ");
				System.out.println("----------------------------------------------------");
				System.out.println("");
				
				appService.subMenuOptions(menu.printSubMenu(dude));
				
				System.out.println("");
				System.out.println("----------------------------------------------------");
				}
				//logic for what they choose
				//call to appService to execute methods based on action.
			}
			
			else if (choice.equals(Quit)) {
				System.exit(0);
			}
			
			else {
				System.out.println("Invalid entry. Please re-enter.");
			}
			/*
			Display the Starting Menu 

			*/
		}
	}


}
