package com.pavlov.dictionary.exception;

public class DictionaryAlreadyInitialized extends Exception{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7257486657899026455L;

	public String getMessage() {
		return "Dictionary already initialized!";
	}

}
