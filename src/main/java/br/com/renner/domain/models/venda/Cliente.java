package br.com.renner.domain.models.venda;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String id;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String nome;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String documento;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String tipoDocumento;

    @NotNull(message = "{campo.notnull}")
    private String tipoPessoa;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String endereco;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String numeroEndereco;

    private String complementoEndereco;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String bairro;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String cidade;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String estado;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String pais;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String cep;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String codigoIbge;

    private String telefone;

    @NotNull(message = "{campo.notnull}")
    @NotBlank(message = "{campo.notblank}")
    private String email;
}
