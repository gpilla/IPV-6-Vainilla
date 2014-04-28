package com.uqbar.vainilla.exceptions;

public class GameException extends RuntimeException {

	// ****************************************************************
	// ** CONSTRUCTORS
	// ****************************************************************

	/**
	 * 
	 */
	private static final long serialVersionUID = -2397610394154582031L;

	public GameException() {
		super();
		// TODO Auto-generated constructor stub
	}


	public GameException(String message, Throwable cause) {
		super(message, cause);
		// TODO Auto-generated constructor stub
	}

	public GameException(Throwable cause) {
		super(cause);
		// TODO Auto-generated constructor stub
	}

	public GameException(String description) {
		super(description);
	}
}