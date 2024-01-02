package br.com.renner.domain.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosConfirmacaoCanal {

    private String numeroPedido;
    private String numeroOrdemExterno;
    private String chaveNFE;
    private String numeroNota;
    private String dataEmissao;
    private String pdf;
    private String status;
}
