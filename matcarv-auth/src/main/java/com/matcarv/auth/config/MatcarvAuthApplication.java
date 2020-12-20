package com.matcarv.auth.config;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 
 * @author weslleymatosdecarvalho
 *
 */
@ApiIgnore
@RestController
@EnableAutoConfiguration
@SpringBootApplication
@ComponentScan(basePackages = {"com.matcarv.auth"})
public class MatcarvAuthApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatcarvAuthApplication.class, args);
	}
	
	/**
	 * 
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		final ReloadableResourceBundleMessageSource messageSource
	      = new ReloadableResourceBundleMessageSource();
	     
	    messageSource.setBasename("classpath:messageSources");
	    messageSource.setDefaultEncoding("UTF-8");
	    
	    return messageSource;
	}
	
	/**
	 * 
	 * @param httpServletResponse
	 */
	@ApiOperation(value = "", hidden = true)
	@RequestMapping(value = "/docs", method = RequestMethod.GET)
	public void initDocs(final HttpServletResponse httpServletResponse) {
	    httpServletResponse.setHeader("Location", "/api/auth/swagger-ui.html");
	    httpServletResponse.setStatus(302);
	}
	
	/**
	 * 
	 * @param httpServletResponse
	 */
	@ApiOperation(value = "", hidden = true)
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public void init(final HttpServletResponse httpServletResponse) {
	    httpServletResponse.setHeader("Location", "/swagger-ui.html");
	    httpServletResponse.setStatus(302);
	}

}
