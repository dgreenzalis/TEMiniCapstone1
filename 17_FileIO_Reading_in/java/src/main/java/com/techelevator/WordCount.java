package com.techelevator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WordCount {

	public static void main(String[] args) {

		// one shot one kill

		// NUMBER OF WORDS --> 30355

		// NUMBER OF SENTENCES --> range 1116 - 1839
		// split based on period, exlcamation point, question mark
		// #cute we can cut search w/ substring to not pull websites n shit

		// ** get super #cute - break up into two private methods **

		// set up file to be read

		File textDoc = new File("alices_adventures_in_wonderland.txt");

		try (Scanner fileScan = new Scanner(textDoc)) {

			int wordCount = 0;
			int sentenceCounter = 0;
			// use while loop to feed in line
			while (fileScan.hasNextLine()) {

				fileScan.skip("[ \n]*");

				//sentences
				String currentLine = fileScan.nextLine();
				
				if (currentLine.contains(".")) {
					sentenceCounter++;
				} else if (currentLine.contains("!")) {
					sentenceCounter++;
				} else if (currentLine.contains("?")) {
					sentenceCounter++;
				}
				
				
				//words
				String[] wordsOnLine = currentLine.split("\\s+");
				String[] specialChar = new String[]{"0","1","2","3","4","5","6","7","8","9","*","http","www"};
			
				
				int numbersInLine = 0;

				for (String s : wordsOnLine) {
					for (String i: specialChar) {
						if(s.contains(i)) {
							numbersInLine++;
						}
					}
					
					

				}
				wordCount = wordCount + wordsOnLine.length - numbersInLine;
			}

			System.out.println(wordCount);
			
			System.out.println(sentenceCounter);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}