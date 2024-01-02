package br.com.renner.infrastructure.adapters.output.rest.proxy.matriztributaria;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.math.BigInteger;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MatrizTributariaResponseEntity {

    private BigInteger sku;
    private BigDecimal valorIcms;
    private BigDecimal valorPis;
    private BigDecimal valorDifaul;
    private BigDecimal valorFcpIcms;
}
