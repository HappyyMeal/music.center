package com.bsuir.center.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR, reason = "Something goes wrong in the Dao layer server side.")
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
