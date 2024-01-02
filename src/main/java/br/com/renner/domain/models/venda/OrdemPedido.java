package br.com.renner.domain.models.venda;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrdemPedido {

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String numeroPedido;

    @Pattern(regexp = "[0-9]{12}\\-[0-9]{1}", message = "{campo.pattern.numero-ordem-externo}")
    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String  numeroOrdemExterno;

    @NotNull(message = "{campo.notnull}")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS")
    private LocalDateTime dataAutorizacao;
}
