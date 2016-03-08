package com.bsuir.center.exception;

public class DaoException extends ContextAppException {

	/**
	 * Serial version ID.
	 */
	private static final long serialVersionUID = -4213065756172644794L;

	public DaoException(String message, Throwable cause) {
		super(message, cause);
	}

	public DaoException(String message) {
		super(message);
	}

	public DaoException(Throwable cause) {
		super(cause);
	}
}
