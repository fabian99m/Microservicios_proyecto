package com.pragma.servicioadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableAdminServer
@SpringBootApplication
public class ServicioAdminApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServicioAdminApplication.class, args);
	}

}
