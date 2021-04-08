package com.stackroute.giphy.exception;

public class GifNotFoundException extends Exception{
	private static final long serialVersionUID = 1L;
	public GifNotFoundException(String message) {
        super(message);
    }
}
