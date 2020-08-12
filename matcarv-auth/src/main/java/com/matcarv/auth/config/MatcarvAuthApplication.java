package com.matcarv.auth.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

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

}
