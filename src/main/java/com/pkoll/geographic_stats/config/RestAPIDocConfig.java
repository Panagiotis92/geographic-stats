package com.pkoll.geographic_stats.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        servers = {
                @Server(url = "${server.servlet.context-path}", description = "Server Context Path")
        }
)
@Configuration
public class RestAPIDocConfig {
    @Bean
    public OpenAPI springShopOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("Geographic Stats")
                        .description("A REST API for geographic statistics")
                        .version("v0.0.1"));
    }
}
