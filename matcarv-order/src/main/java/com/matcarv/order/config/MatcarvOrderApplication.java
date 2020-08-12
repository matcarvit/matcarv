package com.matcarv.order.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
 * @author weslleymatosdecarvalho
 *
 */
@EnableAutoConfiguration
@SpringBootApplication
@EnableCaching
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableRedisRepositories(basePackages = "com.matcarv.**.cache")
@EntityScan( basePackages = {"com.matcarv.order.entities"})
@EnableJpaRepositories(basePackages = { "com.matcarv.order.repository"})
@ComponentScan(basePackages = {"com.matcarv"})
public class MatcarvOrderApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatcarvOrderApplication.class, args);
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
	 * @return
	 */
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
	    return new JedisConnectionFactory();
	}
	 
	/**
	 * 
	 * @return
	 */
	@Bean
	public RedisTemplate<String, Object> redisTemplate() {
	    final RedisTemplate<String, Object> template = new RedisTemplate<>();
	    template.setConnectionFactory(jedisConnectionFactory());
	    
	    return template;
	}
	

}
