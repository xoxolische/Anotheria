package concurrency.dictionary.fix;

import java.util.HashMap;
import java.util.concurrent.locks.ReentrantLock;

import concurrency.dictionary.Dictionary;

/**
 * This class shows how to solve dictionary problem with lock.
 * 
 * @author Nikita Pavlov
 *
 */
public class LockDictionary extends Dictionary {
	private ReentrantLock lock = new ReentrantLock();

	public LockDictionary() {
		super(new HashMap<>());
	}

	@Override
	public void addWord(String w) {
		lock.lock();
		if (this.getDictionary() != null) {
			this.getDictionary().put(w, w);
		}
		lock.unlock();
	}

}
