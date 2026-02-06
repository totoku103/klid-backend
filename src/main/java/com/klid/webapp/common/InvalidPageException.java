package com.klid.webapp.common;

/**
 * 사용자 정의 Exception
 *
 */
@SuppressWarnings("serial")
public class InvalidPageException extends RuntimeException {

	/**
	 * Constructor
	 */
	public InvalidPageException() {
		super();
	}

	/**
	 * Constructor
	 * @param throwable	exception object
	 */
	public InvalidPageException(Throwable throwable) {
		super(throwable);
	}

}
