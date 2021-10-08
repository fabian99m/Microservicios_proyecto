package com.pragma.servicioconfig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;


@EnableConfigServer
@SpringBootApplication
public class ServicioConfigApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioConfigApplication.class, args);
	}

}
