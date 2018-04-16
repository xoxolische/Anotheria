package concurrency.dictionary.fix;

import java.util.concurrent.ConcurrentHashMap;

import concurrency.dictionary.Dictionary;

/**
 * This class shows ho to solve dictionary problem with ConcurrentHashMap.
 * 
 * @author Nikita Pavlov
 *
 */
public class ConcurrentDictionary extends Dictionary {

	public ConcurrentDictionary() {
		super(new ConcurrentHashMap<>());
	}

}
