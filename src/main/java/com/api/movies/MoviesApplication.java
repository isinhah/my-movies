package com.api.movies;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Movies API", description = "API REST para Gerenciamento de Filmes"))
public class MoviesApplication {
	public static void main(String[] args) {
		SpringApplication.run(MoviesApplication.class, args);
	}
}