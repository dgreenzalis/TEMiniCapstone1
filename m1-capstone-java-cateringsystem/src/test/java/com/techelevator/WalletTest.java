package com.techelevator;

import java.io.IOException;
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

	@Before
	public void beforeTest() {
		testInventory = testCateringInventory.generateInventory("cateringSystemJunitTestFile.txt");
	}

	@Test
	public void testWalletBalanceAddInRange() {
		try {
			testWallet.addMoney("1234");
		} catch (IOException e1) {
			System.out.print("whoops)");
		}
		Assert.assertEquals(1234, testWallet.getCurrentAccountBalance(), .01);

	}

	@Test
	public void testWalletBalanceNegative() {
		try {
			testWallet.addMoney("-50");
		} catch (IOException e1) {
			System.out.print("whoops)");
		}
		Assert.assertEquals(0, testWallet.getCurrentAccountBalance(), .01);
	}

	@Test
	public void testWalletBalanceOver5000() {
		try {
			testWallet.addMoney("6000");
		} catch (IOException e1) {
			System.out.print("whoops)");
		}
		Assert.assertEquals(0, testWallet.getCurrentAccountBalance(), .01);
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
			if (tp.getId().equalsIgnoreCase("K4")) {
				testWallet.addToCart(tp, new Integer(17));
				Assert.assertFalse(testWallet.cartMap.containsKey("K4"));
			}
		}
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("D2")) {
				testWallet.addToCart(tp, new Integer(50));
				Assert.assertEquals(new Integer(50), testWallet.cartMap.get(tp));
			}
		}
	}

	@Test
	public void testAddingProductQuantity() {

		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("D3")) {
				testWallet.addToCart(tp, new Integer(67));
				Assert.assertEquals(null, testWallet.cartMap.get(tp));
			}
		}
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("B3")) {
				testWallet.addToCart(tp, new Integer(-12));
				Assert.assertEquals(null, testWallet.cartMap.get(tp));
			}
		}
	}

	@Test
	public void testCheckOut() {
		try {
			testWallet.addMoney("100");
		} catch (IOException e1) {
			System.out.print("whoops)");
		}

		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("A2")) {
				testWallet.addToCart(tp, new Integer("5"));
			} // 14.75
		}
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("D3")) {
				testWallet.addToCart(tp, new Integer("4"));
			} // 4.40
		}
		for (Product tp : testInventory) {
			if (tp.getId().equalsIgnoreCase("B3")) {
				testWallet.addToCart(tp, new Integer("17"));
			} // 43.35
		}

		float testChangeReturned = testWallet.getCurrentAccountBalance() - testWallet.getCartTotalDollarAmount();
		testWallet.checkOut();
		Assert.assertEquals(0.00f, testWallet.getCurrentAccountBalance(), .01);
		Assert.assertTrue(testWallet.cartMap.isEmpty());
		Assert.assertEquals(37.5f, testChangeReturned, .1f);

	}

}