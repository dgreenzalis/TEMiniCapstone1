package com.techelevator;

import java.util.Map;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Assert;   //Use this one...

public class ReadInventoryTest {
	
	CateringInventory cateringInventory= new CateringInventory();
	Map <String, String[]> testMap = cateringInventory.generateInventory("cateringSystemJunitTestFile.txt"); 
	
	
	@Test
	public void testForInventoryPrint() {
		Assert.assertEquals("Monster Energy", testMap.get("B1")[0]);
		Assert.assertEquals("2.55", testMap.get("B3")[1]);
		Assert.assertEquals("D", testMap.get("D3")[2]);
	}

	
}
