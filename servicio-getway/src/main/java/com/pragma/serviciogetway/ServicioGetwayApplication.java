package com.pragma.serviciogetway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServicioGetwayApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioGetwayApplication.class, args);
	}

}
