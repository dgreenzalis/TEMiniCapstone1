package com.techelevator;

import java.util.List;
import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert; //Use this one...

public class QuantitySoldOutTest {
	
	public Product testProduct;
	
	
	@Before
	public void beforeTest() {
		testProduct= new Product();
		testProduct.setQuantity(0);
	}

	@Test
	public void testConfirmSoldOut() {
	
	Assert.assertEquals("SOLD OUT", testProduct.getQuantityToDisplay());
	}
}
