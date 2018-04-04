package concurrency.dictionary.crashtest;

import concurrency.dictionary.Dictionary;

public class Executor implements Runnable{
	
	private Dictionary dictionary;
	
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
