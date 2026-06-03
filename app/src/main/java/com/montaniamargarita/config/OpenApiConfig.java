package com.montaniamargarita.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración de Swagger / OpenAPI.
 * Expone el botón "Authorize" para pegar el JWT obtenido en /auth/login.
 */
@Configuration
public class OpenApiConfig {

    private static final String ESQUEMA_BEARER = "bearerAuth";

    @Bean
    public OpenAPI montaniaOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API Sistema Montaña Margarita")
                        .description("Backend REST para gestión de pedidos, facturación y reportes del restaurante.")
                        .version("1.0.0")
                        .contact(new Contact()
                                .name("Juan Camilo López Díaz")
                                .email("juanca110720@gmail.com"))
                        .license(new License().name("Uso académico")))
                .addSecurityItem(new SecurityRequirement().addList(ESQUEMA_BEARER))
                .components(new Components().addSecuritySchemes(ESQUEMA_BEARER,
                        new SecurityScheme()
                                .type(SecurityScheme.Type.HTTP)
                                .scheme("bearer")
                                .bearerFormat("JWT")
                                .description("Token JWT obtenido en POST /api/v1/auth/login")));
    }
}
