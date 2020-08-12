/**
 * 
 */
package com.matcarv.order.filters;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.GenericFilterBean;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;




/**
 * @author weslleymatosdecarvalho
 *
 */
public class JWTAuthenticationFilter extends GenericFilterBean {

    private static final String SECRET = "MySecret";
	
	private static final String TOKEN_PREFIX = "Bearer";
	
	private static final String HEADER_STRING = "Authorization";
	
	/**
	 * 
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain)
			throws IOException, ServletException {
		try {

			final Authentication authentication = getAuthentication((HttpServletRequest) request);
			
			SecurityContextHolder.getContext().setAuthentication(authentication);
			
			filterChain.doFilter(request, response);
		
		} catch (ExpiredJwtException | UnsupportedJwtException | MalformedJwtException e) {
			((HttpServletResponse) response).sendError(HttpServletResponse.SC_FORBIDDEN, e.getMessage());
			return;
		}
	}
	
	/**
	 * 
	 * @param request
	 * @return
	 */
	private Authentication getAuthentication(final HttpServletRequest request) {
	
		String token = request.getHeader(HEADER_STRING);
		
		if (token != null) {
			final Claims claims = Jwts.parser()
					.setSigningKey(SECRET)
					.parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody();
			
			final String user = claims.getSubject();
			
			if (user != null) {
				final UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null,
						new ArrayList<>());

				return auth;
			}
			
		}

		return null;
	}
}
