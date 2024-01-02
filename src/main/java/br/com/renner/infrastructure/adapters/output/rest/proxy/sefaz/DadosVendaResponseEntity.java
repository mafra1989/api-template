package br.com.renner.infrastructure.adapters.output.rest.proxy.sefaz;

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
public class DadosVendaResponseEntity {

    private String nfeKey;
    private String invoiceNumber;
    private String issuanceDate;
    private String invoice;
}
