package com.techelevator;

public class Customer {

	public Double currentAccountBalance = 0.00;

	public Double getCurrentAccountBalance() {
		return currentAccountBalance;
	}
	
	public Double addMoney(int moneyToAdd) {
		currentAccountBalance += moneyToAdd;
		return currentAccountBalance;
	}

}
