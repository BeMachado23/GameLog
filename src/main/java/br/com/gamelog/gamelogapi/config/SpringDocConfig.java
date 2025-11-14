package br.com.gamelog.gamelogapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("GameLog API 🎮")
                        .description("API RESTful para catalogar e avaliar jogos, desenvolvida como Situação de Aprendizagem.")
                        .version("v1.0.0")
                        .license(new License().name("Licença MIT").url("https://opensource.org/licenses/MIT"))
                );
    }
}