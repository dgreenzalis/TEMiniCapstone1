package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CateringInventory {

	//take list from AppService constructor, split each line, apply to new Product, put into List of Products
	//could have been in AppService, but would have been messier than already is
	public List<Product> generateInventory(String inventoryListName) {

		File inventoryFile = new File(inventoryListName);

		List<Product> inventoryList = new ArrayList<Product>();

		try(Scanner inventoryScanner = new Scanner(inventoryFile)) {
			

			while (inventoryScanner.hasNextLine()) {
				
				String currentLine = inventoryScanner.nextLine();
				String[] currentLineArray = currentLine.split("\\|");
				
				Product product = new Product();
				product.setId(currentLineArray[0]);
				product.setName(currentLineArray[1]);
				product.setPrice(Float.parseFloat(currentLineArray[2]));
				product.setType(currentLineArray[3]);

				inventoryList.add(product);
				}
				
			
		} catch (FileNotFoundException e) {
		}

		return inventoryList;
	}
	
}
