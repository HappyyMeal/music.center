package com.bsuir.client.exception;

public class ServiceException extends ContextAppException {
	private static final long serialVersionUID = -4213065756172644794L;

	public ServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public ServiceException(String message) {
		super(message);
	}

	public ServiceException(Throwable cause) {
		super(cause);
	}
}
