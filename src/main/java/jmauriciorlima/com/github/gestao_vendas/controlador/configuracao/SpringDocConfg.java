package jmauriciorlima.com.github.gestao_vendas.controlador.configuracao;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocConfg {

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API de Gestão de Vendas")
                        .description("Sistema de Gestão de Vendas.")
                        .version("1.0.0"));
    }
}
