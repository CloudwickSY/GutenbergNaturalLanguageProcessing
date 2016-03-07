package NaturalLanguageProcessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class WordCount {
	public static void main(String[] argv) {
		String wordToCount = "the", fileName = "pg2600.txt";
		int count = 0;
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("[^A-z]", " ");
				for (String word : line.split(" ")) {
					if (word.compareToIgnoreCase(wordToCount) == 0) {// word.compareToIgnoreCase(wordToCount)==0
						count++;
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.printf("File not found.\n");
		} catch (IOException e) {
			System.out.printf("BufferReader Error.\n");
		} finally {
			System.out.printf("The occurance of the word %s in the fille was %d.\n", wordToCount, count);
		}
	}
}
