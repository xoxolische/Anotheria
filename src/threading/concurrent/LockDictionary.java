package threading.concurrent;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 * This class shows how we can fix corrupted data in dictionary with Lock.
 * <p>
 * As we can see, after each execution dictionary size is constant.
 * 
 * @author Nikita Pavlov
 *
 */
public class LockDictionary extends Dictionary implements Runnable {
	private ReentrantLock lock = new ReentrantLock();

	public static void main(String[] args) {

		LockDictionary c = new LockDictionary();
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
	public void addWord(String w) {
		if (this.getD() != null) {
			lock.lock();
			this.getD().put(w, w);
			lock.unlock();
		}
	}

	@Override
	public void run() {
		String[] w = this.getWords();
		for (int i = 0; i < w.length; i++) {
			addWord(w[i]);
		}
	}

}
