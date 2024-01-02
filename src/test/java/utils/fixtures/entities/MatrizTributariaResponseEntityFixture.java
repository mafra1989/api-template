package utils.fixtures.entities;

import br.com.renner.infrastructure.adapters.output.rest.proxy.matriztributaria.MatrizTributariaResponseEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;
import java.math.BigInteger;

public class MatrizTributariaResponseEntityFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        entityValido();
    }

    private void entityValido() {
        Fixture.of(MatrizTributariaResponseEntity.class).addTemplate(VALIDO, new Rule() {{
            add("sku", new BigInteger("324226428"));
            add("valorIcms", new BigDecimal(38));
            add("valorPis", new BigDecimal(12));
            add("valorDifaul", new BigDecimal(9));
            add("valorFcpIcms", new BigDecimal(58));
        }});
    }
}
