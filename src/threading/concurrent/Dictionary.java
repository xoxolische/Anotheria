package threading.concurrent;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * Base Dictionary class example.
 * <p>
 * words array contains 466544 unique words.
 * 
 * @author Nikita Pavlov
 * 
 */
public class Dictionary {

	private static Map<String, String> d;
	private static String[] words = getArray(Const.TEST_1_466544);

	public Map<String, String> getD() {
		return d;
	}

	public String[] getWords() {
		return words;
	}

	public void addWord(String word) {
		if (d != null) {
			d.put(word, word);
		}
	}

	synchronized public void initializeDictionary(Map<String, String> m) throws DictionaryAlreadyInitialized {
		if (d == null) {
			d = m;
		} else {
			throw new DictionaryAlreadyInitialized();
		}
	}

	static String[] getArray(String book) {
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
