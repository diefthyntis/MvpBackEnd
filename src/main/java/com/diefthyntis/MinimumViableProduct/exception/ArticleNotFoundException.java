package com.diefthyntis.MinimumViableProduct.exception;

public class ArticleNotFoundException extends RuntimeException {
	 private static final long serialVersionUID = 1L;
	    public ArticleNotFoundException(String sentence) {
	        super(sentence);
	    }
}
