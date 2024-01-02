package utils.fixtures.models;

import br.com.renner.domain.models.MatrizTributaria;
import br.com.renner.infrastructure.adapters.input.rest.dtos.OrdemPedidoDTO;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MatrizTributariaFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        modelValido();
    }

    private void modelValido() {
        Fixture.of(MatrizTributaria.class).addTemplate(VALIDO, new Rule() {{
            add("sku", new BigInteger("324226428"));
            add("valorIcms", new BigDecimal(38));
            add("valorPis", new BigDecimal(12));
            add("valorDifaul", new BigDecimal(9));
            add("valorFcpIcms", new BigDecimal(58));
        }});
    }
}
