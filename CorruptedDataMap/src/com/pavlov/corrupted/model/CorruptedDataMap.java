package com.pavlov.corrupted.model;

import java.util.HashMap;

import com.pavlov.dictionary.exception.DictionaryAlreadyInitialized;
import com.pavlov.dictionary.model.Dictionary;

/**
 * This class tries to add words in our base dictionary in 4 threads and prints
 * dictionary size. As we can see, after each launch we have different
 * dictionary size;
 * 
 * @author Nikita Pavlov
 *
 */
public class CorruptedDataMap extends Dictionary implements Runnable {

	public static void main(String[] args) {

		CorruptedDataMap c = new CorruptedDataMap();
		try {
			c.initializeDictionary(new HashMap<>());
		} catch (DictionaryAlreadyInitialized e1) {
			e1.printStackTrace();
		}
		Thread t0 = new Thread(c);
		Thread t1 = new Thread(c);
		Thread t2 = new Thread(c);
		Thread t3 = new Thread(c);

		t0.start();
		t1.start();
		t2.start();
		t3.start();

		try {
			t0.join();
			t1.join();
			t2.join();
			t3.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println(c.getD().size());
	}

	@Override
	public void run() {
		String[] w = this.getWords();
		for (int i = 0; i < w.length; i++) {
			addWord(w[i]);
		}
	}

}
