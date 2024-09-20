package com.diefthyntis.MinimumViableProduct.exception;

public class CommentNotFoundException extends RuntimeException {
	 private static final long serialVersionUID = 1L;
	    public CommentNotFoundException(String sentence) {
	        super(sentence);
	    }
}
