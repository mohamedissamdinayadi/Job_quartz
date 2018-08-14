package com.example.oauth2monday;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;

@SpringBootApplication
@EnableResourceServer
public class Oauth2mondayApplication {

	public static void main(String[] args) {
		SpringApplication.run(Oauth2mondayApplication.class, args);
	}


}
