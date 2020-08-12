/**
 * 
 */
package com.matcarv.products.config;

import java.util.Arrays;
import java.util.List;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.common.collect.Lists;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.AuthorizationScope;
import springfox.documentation.service.SecurityReference;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author weslleymatosdecarvalho
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerApplication {
	
	/**
	 * 
	 */
	public static final String AUTHORIZATION_HEADER = "Authorization";

	
	/**
	 * 
	 */
	@Autowired
	private MessageSource messageSource;

	/**
	 * 
	 * @return
	 */
	@Bean
	public Docket api() {
	    return new Docket(DocumentationType.SWAGGER_2)
	        .select()
	        .apis(RequestHandlerSelectors.basePackage("com.matcarv.products"))
	        .build()
            .securityContexts(Arrays.asList(securityContext()))
            .securitySchemes(Lists.newArrayList(apiKey()))
	        .apiInfo(metaData());
	 }
	
	/**
	 * 
	 * @return
	 */
	private ApiInfo metaData() {
		return new ApiInfoBuilder()
	        .title(getMessage("application.api.documentation"))
	        .description(getMessage("application.description"))
	        .version(getMessage("application.version"))
	        .license(getMessage("application.license"))
	        .licenseUrl(getMessage("application.license.url"))
	        .build();
	 }
	 
	 /**
	  * 
	  * @return
	  */
	 private ApiKey apiKey() {
		 return new ApiKey("JWT", AUTHORIZATION_HEADER, "header");
	 }

	 /**
	  * 
	  * @return
	  */
	private SecurityContext securityContext() {
        return SecurityContext.builder()
            .securityReferences(defaultAuth())
            .forPaths(PathSelectors.any())
            .build();
    }

	/**
	 * 
	 * @return
	 */
    private List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
            = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Lists.newArrayList(
            new SecurityReference("JWT", authorizationScopes));
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
