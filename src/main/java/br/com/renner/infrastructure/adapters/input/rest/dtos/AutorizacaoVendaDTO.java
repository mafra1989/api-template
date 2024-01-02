package br.com.renner.infrastructure.adapters.input.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorizacaoVendaDTO {

    private String canal;
    private String empresa;
    private String loja;
    private Long pdv;

    private OrdemPedidoDTO ordemPedido = new OrdemPedidoDTO();

    private ClienteDTO cliente = new ClienteDTO();

    private Long totalItens;
    private Long quantidadeItens;

    private List<ItemVendaDTO> itens;

}
