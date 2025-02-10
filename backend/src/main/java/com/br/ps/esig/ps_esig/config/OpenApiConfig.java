package com.br.ps.esig.ps_esig.config;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                        new Info()
                                .title("Rest Api - Para gerenciar tarefas")
                                .description("Api para gerenciar tarefas do processo seletivo esig")
                                .version("v1")
                );
    }
}
