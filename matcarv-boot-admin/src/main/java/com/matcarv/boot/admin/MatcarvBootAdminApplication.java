package com.matcarv.boot.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

@EnableAdminServer
@EnableDiscoveryClient
@EnableAutoConfiguration
@SpringBootApplication
public class MatcarvBootAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(MatcarvBootAdminApplication.class, args);

	}

}
