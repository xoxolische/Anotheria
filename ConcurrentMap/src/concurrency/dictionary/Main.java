package concurrency.dictionary;

import java.util.HashMap;

import concurrency.dictionary.crashtest.Executor;

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

	public static void main(String[] args) {
		new Main().run(new Dictionary(new HashMap<>()), 4);
	}

	/**
	 * 
	 * @param d
	 *            dictionary type
	 * @param t
	 *            number of threads
	 */
	private void run(Dictionary d, int t) {
		Thread[] threads = new Thread[t];
		for (int i = 0; i < threads.length; i++) {
			threads[i] = new Thread(new Executor(d));
			threads[i].start();
		}
		for (Thread c : threads) {
			try {
				c.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println(d.getDictionary().size());
	}

}
