package com.techelevator;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;   //Use this one...



public class WalletTest {

	Wallet testDude = new Wallet();
	AppService appService = new AppService("cateringSystemJunitTestFile.txt");
	
	@Test
	public void testForDudesBalance() {
		Assert.assertEquals(testDude.getCurrentAccountBalance(),appService.appServCustomer.addMoney());
		
		Assert.fail();
	}

	
	/*
	
	if(subMenuInput.equals("1")) {
			appServCustomer.addMoney();
			System.out.println(appServCustomer.getCurrentAccountBalance());
	*/
}
