/**
 * 
 */
package com.matcarv.auth.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.matcarv.auth.filters.JWTAuthenticationFilter;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Configuration
@EnableWebSecurity
public class WebSecurityApplication extends WebSecurityConfigurerAdapter {
	
	@Autowired
	private JWTAuthenticationFilter jwtAuthenticationFilter;

	/**
	 * 
	 */
	@Override
	protected void configure(final HttpSecurity httpSecurity) throws Exception {
		httpSecurity.csrf().disable().authorizeRequests()
			.antMatchers("/docs").permitAll()
			.antMatchers(
	                HttpMethod.GET,
	                "/info",
	                "/v2/api-docs",
	                "/webjars/**",            
	                "/swagger-resources/**",  
	                "/configuration/**",      
	                "/*.html",
	                "/favicon.ico",
	                "/actuator/**",
	                "/**/*.html",
	                "/**/*.css",
	                "/**/*.js"
	        ).permitAll()
			.antMatchers(HttpMethod.POST, "/authenticate").permitAll()
			.anyRequest().authenticated()
			.and()
			.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
	@Override
	protected void configure(final AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser("admin")
			.password(passwordEncoder().encode("123456")).roles("ADMIN");
	}
	
	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
