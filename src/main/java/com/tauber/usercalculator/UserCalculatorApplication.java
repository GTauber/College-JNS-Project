package com.tauber.usercalculator;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "User Calculator API", version = "1.0", description = "User Calculator API"))
public class UserCalculatorApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserCalculatorApplication.class, args);
	}

}
