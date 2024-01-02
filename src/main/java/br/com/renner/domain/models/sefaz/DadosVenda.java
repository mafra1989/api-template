package br.com.renner.domain.models.sefaz;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DadosVenda {

    private String orderNumber;
    private String externalOrderNumber;
    private Customer customer;
    private List<Product> products;
}
