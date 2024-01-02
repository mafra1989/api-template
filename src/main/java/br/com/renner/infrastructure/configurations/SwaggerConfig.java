package br.com.renner.infrastructure.configurations;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                title = "RENNER API - Orquestrador de Vendas",
                description = "API para montar a matriz tributária dos produtos, autorizar as vendas junto a SEFAZ, \n" +
                        "informar a nota fiscal para o canal (e para o cliente), como também, registrar todas as vendas em seu banco de dados."
        )
)
@Configuration
public class SwaggerConfig {
}
