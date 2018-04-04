package concurrency.dictionary.fix;

import java.util.concurrent.ConcurrentHashMap;

import concurrency.dictionary.Dictionary;

public class ConcurrentDictionary extends Dictionary{

	public ConcurrentDictionary() {
		super(new ConcurrentHashMap<>());
	}

}
