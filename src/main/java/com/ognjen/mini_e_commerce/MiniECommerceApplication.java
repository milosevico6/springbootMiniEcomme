package com.ognjen.mini_e_commerce;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(info = @Info(title = "Mini E-commerce API", version = "1.0.0"))
@SpringBootApplication
public class MiniECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(MiniECommerceApplication.class, args);
	}

}
