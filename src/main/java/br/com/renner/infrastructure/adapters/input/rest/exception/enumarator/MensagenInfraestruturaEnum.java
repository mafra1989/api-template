package br.com.renner.infrastructure.adapters.input.rest.exception.enumarator;


import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagenInfraestruturaEnum {

    FALHA_PROCESSAMENTO("Falha no processamento da requisição."),
    DATA_INVALIDA("Campo data com formato inválido."),
    VALOR_INVALIDO("Campo numérico inválido."),
    ERRO_JSON("Erro genérico ao mapear o JSON."),
    EM_PROCESSAMENTO("EM_PROCESSAMENTO");

    private final String mensagem;
}