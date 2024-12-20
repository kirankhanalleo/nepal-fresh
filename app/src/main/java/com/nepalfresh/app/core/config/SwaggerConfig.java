package com.nepalfresh.app.core.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info().title("Fresh Nepal Customer App APIs"))
                .addSecurityItem(new io.swagger.v3.oas.models.security.SecurityRequirement().addList("JavaInUseSecurityScheme"))
                .components(new Components().addSecuritySchemes("JavaInUseSecurityScheme", new io.swagger.v3.oas.models.security.SecurityScheme()
                        .name("JavaInUseSecurityScheme").type(io.swagger.v3.oas.models.security.SecurityScheme.Type.HTTP).scheme("bearer").bearerFormat("JWT")));

    }
}