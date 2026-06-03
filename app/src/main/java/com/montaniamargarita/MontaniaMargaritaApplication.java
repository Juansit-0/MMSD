package com.montaniamargarita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Punto de entrada de la aplicación Spring Boot.
 * Inicia el servidor embebido (Tomcat) en el puerto configurado en application.yml.
 */
@SpringBootApplication
public class MontaniaMargaritaApplication {

    public static void main(String[] args) {
        SpringApplication.run(MontaniaMargaritaApplication.class, args);
    }
}
