package NaturalLanguageProcessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Locale;

public class WordCount {
	public static void main(String[] argv) {
		String wordToCount = "the", fileName = "pg2600.txt";
		//int count = 0;
		HashMap<String,Integer> wordHashMap = new HashMap<String,Integer>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("[^A-z]", " ");
				for (String word : line.split(" ")){
					word=word.toLowerCase(Locale.ENGLISH);
					if(wordHashMap.containsKey(word)){
						wordHashMap.put(word, wordHashMap.get(word)+1);
					}else{
						wordHashMap.put(word,1);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.printf("File not found.\n");
		} catch (IOException e) {
			System.out.printf("BufferReader Error.\n");
		} finally {
			//System.out.printf("The occurance of the word %s in the fille was %d.\n", wordToCount, count);
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("tf.csv", "UTF-8");
			writer.println("word, frequency");
			for (String word : wordHashMap.keySet()){
				writer.printf("%s, %d\n",word, wordHashMap.get(word));
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			writer.close();
		}
	}
}
