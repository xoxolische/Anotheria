package concurrency.dictionary.fix;

import java.util.Collections;
import java.util.HashMap;

import concurrency.dictionary.Dictionary;

public class SynchronizedDictionary extends Dictionary{

	public SynchronizedDictionary() {
		super(Collections.synchronizedMap(new HashMap<>()));
	}

}
