/**
 * 
 */
package com.matcarv.commons.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.lang.Nullable;
import org.springframework.web.server.ResponseStatusException;

/**
 * @author weslleymatosdecarvalho
 *
 */
public class BusinessException extends ResponseStatusException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1614612962556466856L;

	/**
	 * 
	 * @param status
	 */
	public BusinessException(final HttpStatus status) {
		super(status);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * Constructor with a response status and a reason to add to the exception
	 * message as explanation.
	 * @param status the HTTP status (required)
	 * @param reason the associated reason (optional)
	 */
	public BusinessException(final HttpStatus status, @Nullable final String reason) {
		super(status, reason, null);
	}
}
