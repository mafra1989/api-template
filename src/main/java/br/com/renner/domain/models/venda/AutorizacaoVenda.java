package br.com.renner.domain.models.venda;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AutorizacaoVenda {

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String canal;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String empresa;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String loja;

    @NotNull(message = "{campo.notnull}")
    private Long pdv;

    @Valid
    private OrdemPedido ordemPedido;

    @Valid
    private Cliente cliente;

    @NotNull(message = "{campo.notnull}")
    private Long totalItens;

    @NotNull(message = "{campo.notnull}")
    private Long quantidadeItens;

    @Valid
    @NotNull(message = "{campo.notnull}")
    @NotEmpty(message = "{campo.empty}")
    private List<Item> itens;

}
