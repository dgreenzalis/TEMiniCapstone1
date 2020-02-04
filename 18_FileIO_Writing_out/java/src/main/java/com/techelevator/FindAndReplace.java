package com.techelevator;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class FindAndReplace {

	public static void main(String[] args) throws IOException {

		File sourceFile;
		File destinationFile;
		String searchWord;
		String newWord;

		// Set up file scanner
		try (Scanner inputScan = new Scanner(System.in)) {

			// Prompt user for "Search Word"
			System.out.println("What is the search word?");
			searchWord = inputScan.nextLine();

			// Prompt user to replace the "Search Word" with another word.
			System.out.println("What is the word to replace the search word with?");
			newWord = inputScan.nextLine();

			// Prompt for source file.
			System.out.println("What is the source file?");
			sourceFile = new File(inputScan.nextLine());
			// Check to see if source file exists.

			// If file exists, print name, absolute path, directory type and file type.
			boolean whileRepeat = false;
			while (whileRepeat == false) {

				if (sourceFile.exists()) {
					System.out.println("name: " + sourceFile.getName());
					System.out.println("absolutePath: " + sourceFile.getAbsolutePath());
					if (sourceFile.isDirectory()) {
						System.out.println("type: directory");
					} else if (sourceFile.isFile()) {
						System.out.println("type: file");

					}
					System.out.println("size: " + sourceFile.length());
					whileRepeat = true;
				} else {
					System.out.println(sourceFile.getAbsolutePath() + " does not exist.");
					System.out.println("What is the source file?");
					sourceFile = new File(inputScan.nextLine());
				}
			}

			// Prompt user for destination file.(If the file already exists, it should be
			// overwritten. If the user enters an invalid destination file, the program will
			// indicate this and exit.)
			System.out.println("What is the destination file?");
			destinationFile = new File(inputScan.nextLine());
		}
		if (destinationFile.exists()) {
			destinationFile.delete();
			destinationFile.createNewFile();
		} else {
			destinationFile.createNewFile();
		}

		// Read through each line for searchWord.
		// Replace searchWord with newWord.
		// Write line to destinationFile.

		try (Scanner readerScan = new Scanner(sourceFile)) {

			try (PrintWriter writer = new PrintWriter(destinationFile)) {

				while (readerScan.hasNextLine()) {

					String currentLine = readerScan.nextLine();

					if (currentLine.contains(searchWord)) {
						currentLine = currentLine.replaceAll(searchWord, newWord);
						writer.println(currentLine);
					} else {
						writer.println(currentLine);
					}

				}

			}

		}

	}
}
