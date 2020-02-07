package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class CateringInventory {

//	private Scanner inventoryScanner;

	
//	public String generateInventoryTWO (String inventoryListNameTwo) {
		
		
//	}
	
	
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

	/*
	public Map<String, String[]> generateInventory(String inventoryListName) {

		File inventoryFile = new File(inventoryListName);

		Map<String, String[]> inventoryMap = new HashMap<String, String[]>();

		try(Scanner inventoryScanner = new Scanner(inventoryFile)) {
			

			while (inventoryScanner.hasNextLine()) {
				String currentLine = inventoryScanner.nextLine();
				String[] currentLineArray = currentLine.split("\\|");

				String itemId = currentLineArray[0];
				String[] itemInfoArray = new String[3];

				for (int i = 1; i < 4; i++) {
					itemInfoArray[i - 1] = currentLineArray[i];
				}
				inventoryMap.put(itemId, itemInfoArray);
			}
		} catch (FileNotFoundException e) {
		}

		return inventoryMap;
	}

	
	*/
	
	
}
