package com.java.springbootthymeleaf.Exceptions;

public class PasswordLengthException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PasswordLengthException(String msg) {
		super(msg);
	}

}
