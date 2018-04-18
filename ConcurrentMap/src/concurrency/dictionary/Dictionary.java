package concurrency.dictionary;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * This class represents dictionary. Contains 466544 unique words from file.
 * 
 * @author Nikita Pavlov
 * 
 */
public class Dictionary {

	private Map<String, String> d;
	private static String[] words = getArray(Const.TEST_1_466544);

	/**
	 * Constructor
	 * 
	 * @param m
	 *            pass Map implementation for different situations
	 */
	public Dictionary(Map<String, String> m) {
		this.d = m;
	}

	/**
	 * Adds word to our dictionary.
	 * 
	 * @param word
	 */
	public void addWord(String word) {
		if (d != null) {
			d.put(word, word);
		}
	}

	public Map<String, String> getDictionary() {
		return d;
	}

	public String[] getWords() {
		return words;
	}

	private static String[] getArray(String book) {
		try {
			File f = new File(book);
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String w = br.readLine();
			List<String> l = new LinkedList<>();

			while (w != null) {
				l.add(w);
				w = br.readLine();
			}
			br.close();
			return l.toArray(new String[l.size()]);
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

}
