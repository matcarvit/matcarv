/**
 * 
 */
package com.matcarv.auth.services;

import java.util.Collections;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Component
public class TokenAuthenticationService {

	private static final long EXPIRATION_TIME = 860_000_000;
	
	private static final String SECRET = "MySecret";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/**
	 * 
	 * @param response
	 * @param username
	 */
	public String addAuthentication(final String username) {
		String JWT = Jwts.builder()
				.setSubject(username)
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(SignatureAlgorithm.HS512, SECRET)
				.compact();
		
		return TOKEN_PREFIX + " " + JWT;
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	public Authentication getAuthentication(final HttpServletRequest request) {
		final String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			final String user = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody()
					.getSubject();
			
			if (user != null) {
				return new UsernamePasswordAuthenticationToken(user, null, Collections.emptyList());
			}
		}
		
		return null;
	}
}
