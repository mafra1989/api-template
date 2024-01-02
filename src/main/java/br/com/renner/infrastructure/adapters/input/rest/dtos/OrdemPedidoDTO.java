package br.com.renner.infrastructure.adapters.input.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdemPedidoDTO {

    private String numeroPedido;
    private String  numeroOrdemExterno;
    private String dataAutorizacao;
}
