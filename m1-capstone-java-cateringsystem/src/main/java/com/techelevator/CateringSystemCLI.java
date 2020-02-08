package com.techelevator;

import java.io.File;
import java.io.IOException;
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
	private AuditLog auditLog = new AuditLog();
	
	public CateringSystemCLI(OurMenu menu) {
		this.menu = menu;
	}
	
	public static void main(String[] args) throws IOException {
		OurMenu menu = new OurMenu();
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();
	}	

	public void run() throws IOException {
		
		auditLog.initiateAuditLog("CLI-Audit");
		
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
