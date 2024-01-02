package br.com.renner.domain.enumerators;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MensagensNegociosEnum {

    ERRO_INTERNO("10","Erro na chamada do serviço externo."),
    PROCESSADO("20","PROCESSADO"),
    ERRO("30","ERRO");

    private final String codigo;
    private final String mensagem;

}
