package com.diefthyntis.MinimumViableProduct.exception;

public class TopicNotFoundException extends RuntimeException {
	 private static final long serialVersionUID = 1L;
	    public TopicNotFoundException(String sentence) {
	        super(sentence);
	    }
}
