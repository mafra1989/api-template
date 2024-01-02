package br.com.renner.domain.models.venda;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @NotNull(message = "{campo.notnull}")
    private Long sku;

    @NotNull(message = "{campo.notnull}")
    private Long quantidade;

    @NotNull(message = "{campo.notnull}")
    private BigDecimal valor;
}
