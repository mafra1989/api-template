package br.com.renner.domain.models.sefaz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Product {

    private Long sku;
    private Long amount;
    private BigDecimal value;
    private BigDecimal icmsValue;
    private BigDecimal pisValue;
    private BigDecimal difaulValue;
    private BigDecimal fcpIcmsValue;
}
