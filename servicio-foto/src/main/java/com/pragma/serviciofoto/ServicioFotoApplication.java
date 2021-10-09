package com.pragma.serviciofoto;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class ServicioFotoApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioFotoApplication.class, args);
	}

}
