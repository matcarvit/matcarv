/**
 * 
 */
package com.matcarv.auth.resources;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.matcarv.auth.dtos.AccountCredentialDTO;
import com.matcarv.auth.services.TokenAuthenticationService;

import lombok.Getter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@RestController
public class LogonResource {

	/**
	 * 
	 */
	@Getter
	@Autowired
	private AuthenticationManager authenticationManager;
	
	/**
	 * 
	 */
	@Getter
	@Autowired
	private TokenAuthenticationService tokenAuthenticationService;

	/**
	 * 
	 * @param credentials
	 * @return
	 */
	@PutMapping(path = "/authenticate")
	public String authenticate(final AccountCredentialDTO credentials) {
		final Authentication auth = getAuthenticationManager().authenticate(
				new UsernamePasswordAuthenticationToken(
						credentials.getUsername(), 
						credentials.getPassword(), 
						Collections.emptyList()
						)
				);
		
		return tokenAuthenticationService.addAuthentication(auth.getName());
	}
	
	
}
