package com.techelevator;

import java.util.List;

import org.junit.Test;
import org.junit.Assert; //Use this one...

public class ReadInventoryTest {

	CateringInventory cateringInventory = new CateringInventory();
	List<Product> testList = cateringInventory.generateInventory("cateringSystemJunitTestFile.txt");

	@Test
	public void testForInventoryPrint() {
		for (Product p : testList) {
			if (p.getId().equals("B1")) {
				Assert.assertEquals("Monster Energy", p.getName());
			} else if (p.getId().equals("B3")) {
				Assert.assertEquals((float) 2.55, p.getPrice(), 0.01);
			} else if (p.getId().equals("D3")) {
				Assert.assertEquals("D", p.getType());
			}
		}
	}
}
