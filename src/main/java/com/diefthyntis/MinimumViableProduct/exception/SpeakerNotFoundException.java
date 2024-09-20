package com.diefthyntis.MinimumViableProduct.exception;



public class SpeakerNotFoundException extends RuntimeException {
	  private static final long serialVersionUID = 1L;
	    public SpeakerNotFoundException(String sentence) {
	        super(sentence);
	    }
}


