package com.proyecto131.escuelas_deportivas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;

@SpringBootApplication
public class EscuelasDeportivasApplication {

	public static void main(String[] args) {
		SpringApplication.run(EscuelasDeportivasApplication.class, args);
	}

	@GetMapping("/")
	public String home() {
		return "index";
	}
}
