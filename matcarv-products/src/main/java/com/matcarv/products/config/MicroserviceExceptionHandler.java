package com.matcarv.products.config;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.server.ResponseStatusException;

import com.matcarv.commons.exceptions.BusinessException;

/**
 * 
 * @author weslleymatosdecarvalho
 *
 */
@ControllerAdvice
public class MicroserviceExceptionHandler {

	/**
	 * 
	 * @param ex
	 * @param request
	 * @return
	 */
	@ExceptionHandler({ BusinessException.class })
    public final ResponseEntity<?> handleException(final ResponseStatusException ex, final WebRequest request) {
		return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ex.getReason());
	}
}
