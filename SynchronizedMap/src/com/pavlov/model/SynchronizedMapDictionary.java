package com.pavlov.model;

import java.util.Collections;
import java.util.HashMap;

import com.pavlov.dictionary.exception.DictionaryAlreadyInitialized;
import com.pavlov.dictionary.model.Dictionary;

/**
 * <p>
 * This class shows how we can fix corrupted data in dictionary with
 * synchronizedMap.
 * <p>
 * As we can see, after each execution dictionary size is constant.
 * 
 * @author Nikita Pavlov
 *
 */
public class SynchronizedMapDictionary extends Dictionary implements Runnable {

	public static void main(String[] args) {

		SynchronizedMapDictionary c = new SynchronizedMapDictionary();
		try {
			c.initializeDictionary(Collections.synchronizedMap(new HashMap<>()));
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
