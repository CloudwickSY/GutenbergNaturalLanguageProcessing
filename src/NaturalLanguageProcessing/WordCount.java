package NaturalLanguageProcessing;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
/*
 * Goal :
 * Can't save to file
 * Can't figure out duplicates
 * Sorting
 */

public class WordCount {
	public static void main(String[] argv) {
		String fileName = "pg2600.txt";
		HashMap<String, Integer> wordHashMap = new HashMap<String, Integer>();
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(fileName));
			String line = null;
			while ((line = br.readLine()) != null) {
				line = line.replaceAll("[^A-z]", " ");
				for (String word : line.split(" ")) {
					word = word.toLowerCase(Locale.ENGLISH);
					if (word.isEmpty()) {
						continue;
					}
					if (wordHashMap.containsKey(word)) {
						wordHashMap.put(word, wordHashMap.get(word) + 1);
					} else {
						wordHashMap.put(word, 1);
					}
				}
			}
		} catch (FileNotFoundException e) {
			System.out.printf("File not found.\n");
		} catch (IOException e) {
			System.out.printf("BufferReader Error.\n");
		} finally {
			// System.out.printf("The occurrence of the word %s in the file was
			// %d.\n", wordToCount, count);
		}
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("tf.csv", "UTF-8");
			writer.println("word, frequency");
			for (String word : wordHashMap.keySet()) {
				writer.printf("%s, %d\n", word, wordHashMap.get(word));
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}

		ArrayList<Map.Entry<String, Integer>> sortedList = entriesSortedByValues(wordHashMap);
		try {
			writer = new PrintWriter("top10.csv", "UTF-8");
			writer.println("word, frequency");
			Map.Entry<String, Integer> entry;
			for (int i = 0; i < Math.min(10, sortedList.size()); i++) {
				entry = sortedList.get(i);
				writer.printf("%s, %d\n", entry.getKey(), entry.getValue());
			}
		} catch (FileNotFoundException | UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			writer.close();
		}

	}

	static <K, V extends Comparable<? super V>> ArrayList<Map.Entry<K, V>> entriesSortedByValues(Map<K, V> map) {
		ArrayList<Map.Entry<K, V>> sortedEntries = new ArrayList<Map.Entry<K, V>>(map.entrySet());
		Collections.sort(sortedEntries, new Comparator<Map.Entry<K, V>>() {
			@Override
			public int compare(Map.Entry<K, V> e1, Map.Entry<K, V> e2) {
				return e2.getValue().compareTo(e1.getValue());
			}
		});
		return sortedEntries;
	}
}
