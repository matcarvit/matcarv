/**
 * 
 */
package com.matcarv.auth.resources;

import java.util.Locale;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author weslleymatosdecarvalho
 *
 */
@RestController
public class InformationResource {
	
	/**
	 * 
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * 
	 * @param httpServletResponse
	 */
	@RequestMapping(value = "/docs", method = RequestMethod.GET)
	public void method(final HttpServletResponse httpServletResponse) {
	    httpServletResponse.setHeader("Location", "/api/auth/swagger-ui.html");
	    httpServletResponse.setStatus(302);
	}
	
	/**
	 * 
	 * @return
	 */
	@RequestMapping(value = "/info", method = RequestMethod.GET)
	public String info() {
	    return getMessage("application.api.documentation");
	}
	
    /**
     * 
     * @param code
     * @return
     */
	 private String getMessage(String code) {
		 return messageSource.getMessage(code, null, Locale.getDefault());
	 }
	
}
