package com.techelevator;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class CateringInventory {

//	private Scanner inventoryScanner;

	
//	public String generateInventoryTWO (String inventoryListNameTwo) {
		
		
//	}
	
	
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

}
