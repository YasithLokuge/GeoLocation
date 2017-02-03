package com.yasith.geolocation.Exception;

/**
 * The Class PCRException.
 */
public class GEOException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public GEOException(String message) {
        super(message);
    }

    public GEOException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
