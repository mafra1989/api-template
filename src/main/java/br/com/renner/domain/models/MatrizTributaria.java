package br.com.renner.domain.models;

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
public class MatrizTributaria {

    private BigInteger sku;
    private BigDecimal valorIcms;
    private BigDecimal valorPis;
    private BigDecimal valorDifaul;
    private BigDecimal valorFcpIcms;
}
