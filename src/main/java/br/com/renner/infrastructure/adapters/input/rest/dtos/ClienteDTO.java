package br.com.renner.infrastructure.adapters.input.rest.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ClienteDTO {

    private String id;
    private String nome;
    private String documento;
    private String tipoDocumento;
    private String tipoPessoa;
    private String endereco;
    private String numeroEndereco;
    private String complementoEndereco;
    private String bairro;
    private String cidade;
    private String estado;
    private String pais;
    private String cep;
    private String codigoIbge;
    private String telefone;
    private String email;
}
