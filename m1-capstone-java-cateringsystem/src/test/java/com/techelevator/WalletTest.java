package com.techelevator;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert; //Use this one...

public class WalletTest {

	Wallet testWallet = new Wallet();
	AppService appService = new AppService("cateringSystemJunitTestFile.txt");
	CateringInventory testCateringInventory = new CateringInventory();
	List<Product> testInventory;
//	Map<Product, Integer> testCart;

	@Before
	public void beforeTest() {
		testInventory = testCateringInventory.generateInventory("cateringSystemJunitTestFile.txt");
//		testCart = new HashMap<Product, Integer>();

	}

	@Test
	public void testWalletBalance() {
	//Adding 1234
	//adding -50
	// adding 6000
		Assert.assertEquals(testWallet.getCurrentAccountBalance(), appService.appServWallet.addMoney());

	}

	@Test
	public void testAddingToCart() {
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("B4")) {
				testWallet.addToCart(tp, new Integer(4));
				Assert.assertEquals(new Integer(4), testWallet.cartMap.get(tp));
			}
		}
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("E3")) {
				testWallet.addToCart(tp, new Integer(17));
				Assert.assertEquals(new Integer(17), testWallet.cartMap.get(tp));
				
			}
		
		}
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("D2")) {
				testWallet.addToCart(tp, new Integer(50));
				Assert.assertEquals(new Integer(50), testWallet.cartMap.get(tp));

			}
		}
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("C3")) {
				testWallet.addToCart(tp, new Integer(67));
				Assert.assertEquals(null, testWallet.cartMap.get(tp));
			}
		}	
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("C3")) {
				testWallet.addToCart(tp, new Integer(-12));
				Assert.assertEquals(null, testWallet.cartMap.get(tp));
			}
		}
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("C3")) {
				testWallet.addToCart(tp, new Integer(""));
				Assert.assertEquals(null, testWallet.cartMap.get(tp));
			}
		}
		
		
		
		
	}
}	