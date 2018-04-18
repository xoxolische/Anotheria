package concurrency.dictionary.crashtest;

import concurrency.dictionary.Dictionary;

/**
 * This class give opportunity to execute our dictionary in multiple threads.
 * 
 * @author Nikita Pavlov
 *
 */
public class Executor implements Runnable {

	private Dictionary dictionary;

	/**
	 * Constructor
	 * 
	 * @param d
	 *            dictionary to execute
	 */
	public Executor(Dictionary d) {
		dictionary = d;
	}

	@Override
	public void run() {
		String[] w = dictionary.getWords();
		for (int i = 0; i < w.length; i++) {
			dictionary.addWord(w[i]);
		}
	}

}
