package com.stackroute.giphy.exception;

public class FeedbackAlreadyExists extends Exception {
	private static final long serialVersionUID = 1L;
	public FeedbackAlreadyExists(String message) {
        super(message);
    }
}
