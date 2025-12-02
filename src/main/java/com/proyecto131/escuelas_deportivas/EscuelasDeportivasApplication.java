package com.proyecto131.escuelas_deportivas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EscuelasDeportivasApplication {

    public static void main(String[] args) {
        SpringApplication.run(EscuelasDeportivasApplication.class, args);
        System.out.println("Aplicación iniciada en: http://localhost:1234");
    }
}