package com.matcarv.products.config;

import java.util.stream.Stream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.CommandLineRunner;
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
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.matcarv.products.entities.Category;
import com.matcarv.products.repository.CategoryRepository;

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
@EnableCaching
@EnableScheduling
@EnableJpaAuditing
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableRedisRepositories(basePackages = {"com.matcarv.**.cache"})
@EntityScan(basePackages = {"com.matcarv.products.entities"})
@EnableJpaRepositories(basePackages = {"com.matcarv.products.repository"})
@ComponentScan(basePackages = {"com.matcarv"})
public class MatcarvProductsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatcarvProductsApplication.class, args);
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
	 * @param repository
	 * @return
	 */
	@Bean
    public CommandLineRunner init(final CategoryRepository repository) {
        return args -> {
            Stream.of("Category A", "Category B", "Category C", "Category D", "Category E").forEach(description -> {
                final Category entity = new Category();
                entity.setDescription(description);
                
                repository.save(entity);
            });
            
            repository.findAll().forEach(System.out::println);
        };
    }
	
	/**
	 * 
	 * @param httpServletResponse
	 */
	@ApiOperation(value = "", hidden = true)
	@RequestMapping(value = "/docs", method = RequestMethod.GET)
	public void initDocs(final HttpServletResponse httpServletResponse) {
	    httpServletResponse.setHeader("Location", "/api/products/swagger-ui.html");
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
