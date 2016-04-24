package com.bsuir.client.exception;

public class ContextAppException extends Exception {
	private static final long serialVersionUID = -5113065756172644794L;

	public ContextAppException(String message, Throwable cause) {
		super(message, cause);
	}

	public ContextAppException(String message) {
		super(message);
	}

	public ContextAppException(Throwable cause) {
		super(cause);
	}

}
