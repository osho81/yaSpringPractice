package com.osho.restBasics;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

//	Uncomment/comment for keycloak
//import org.keycloak.adapters.springboot.KeycloakSpringBootConfigResolver;

@SpringBootApplication
public class RestBasicsApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestBasicsApplication.class, args);
	}

//	Uncomment/comment for keycloak
//	@Bean // Instead of keycloak.json
//	public KeycloakSpringBootConfigResolver keycloakConfigResolver() {
//		return new KeycloakSpringBootConfigResolver();
//	}

}
