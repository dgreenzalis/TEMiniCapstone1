package com.techelevator;

import com.techelevator.view.Menu;

public class CateringSystemCLI {

	private Menu menu;

	public CateringSystemCLI(Menu menu) {
		this.menu = menu;
	}
	
	public static void main(String[] args) {
		Menu menu = new Menu();
		CateringSystemCLI cli = new CateringSystemCLI(menu);
		cli.run();
	}	

	public void run() {
		while (true) {
			/*
			Display the Starting Menu 

			*/
		}
	}


}
