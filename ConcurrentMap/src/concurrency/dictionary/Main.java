package concurrency.dictionary;

import java.util.HashMap;

import concurrency.dictionary.crashtest.Executor;
import concurrency.dictionary.fix.ConcurrentDictionary;
import concurrency.dictionary.fix.LockDictionary;
import concurrency.dictionary.fix.SynchronizedDictionary;
import concurrency.dictionary.fix.SynchronizedKeyWord;

/**
 * This class fills up our dictionaries with words in multiple threads. As we
 * can see, HashMap without synchronization represents each time different value
 * of size. However, synchronized Maps return every time the same correct value
 * : 466544 words.
 * 
 * @author Nikita Pavlov
 *
 */
public class Main {
	private final static int THREADS = 4;

	public static void main(String[] args) {

		Dictionary[] d = { new Dictionary(new HashMap<>()), new SynchronizedKeyWord(), new LockDictionary(),
				new ConcurrentDictionary(), new SynchronizedDictionary() };

		for (int di = 0; di < d.length; di++) {
			Thread[] threads = new Thread[THREADS];

			for (int i = 0; i < threads.length; i++) {
				threads[i] = new Thread(new Executor(d[di]));
				threads[i].start();
			}
			for (Thread t : threads) {
				try {
					t.join();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			System.out.println(d[di].getDictionary().size());
		}

	}

}
