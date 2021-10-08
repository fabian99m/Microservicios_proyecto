package com.pragma.servicioregistro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class ServicioRegistroApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioRegistroApplication.class, args);
	}

}
