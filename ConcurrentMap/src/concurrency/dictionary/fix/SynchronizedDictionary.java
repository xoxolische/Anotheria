package concurrency.dictionary.fix;

import java.util.Collections;
import java.util.HashMap;

import concurrency.dictionary.Dictionary;

/**
 * This class shows how to solve dictionary problem with synchronizedMap.
 * 
 * @author Nikita Pavlov
 *
 */
public class SynchronizedDictionary extends Dictionary {

	public SynchronizedDictionary() {
		super(Collections.synchronizedMap(new HashMap<>()));
	}

}
