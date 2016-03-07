package NaturalLanguageProcessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WordCount {
	public static void main(String[] argv) {
		String wordToCount = "man", fileName = "pg2600.txt";
		int count = 0;
		BufferedReader br;
		
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			Pattern pattern = Pattern.compile("\\b"+wordToCount+"\\b");
			while ((line = br.readLine()) != null) {
				Matcher matcher = pattern.matcher(line);
				while(matcher.find()) count++;
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
