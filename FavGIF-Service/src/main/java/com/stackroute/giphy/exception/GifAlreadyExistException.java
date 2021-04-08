package com.stackroute.giphy.exception;


public class GifAlreadyExistException extends Exception{
	private static final long serialVersionUID = 1L;
	public GifAlreadyExistException(String message) {
        super(message);
    }
}
