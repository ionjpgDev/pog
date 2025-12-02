package com.proyecto131.escuelas_deportivas;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EscuelasDeportivasApplication {

    public static void main(String[] args) {
        SpringApplication.run(EscuelasDeportivasApplication.class, args);
        System.out.println("✅ Aplicación iniciada en: http://localhost:8080");
        System.out.println("✅ Consola H2: http://localhost:8080/h2-console");
        System.out.println("✅ JDBC URL: jdbc:h2:mem:escueladb");
        System.out.println("✅ Usuario: sa");
        System.out.println("✅ Contraseña: (vacía)");
    }
}