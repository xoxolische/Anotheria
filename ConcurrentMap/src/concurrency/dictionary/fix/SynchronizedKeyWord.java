package concurrency.dictionary.fix;

import java.util.HashMap;

import concurrency.dictionary.Dictionary;

public class SynchronizedKeyWord extends Dictionary{

	public SynchronizedKeyWord() {
		super(new HashMap<>());
	}
	
	@Override
	synchronized public void addWord(String w) {
		if (this.getDictionary() != null) {
			this.getDictionary().put(w, w);
		}
	}

}
