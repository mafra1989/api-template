package utils.fixtures.models;

import br.com.renner.domain.models.sefaz.Customer;
import br.com.renner.domain.models.sefaz.DadosVenda;
import br.com.renner.domain.models.sefaz.Product;
import br.com.renner.infrastructure.adapters.input.rest.dtos.ItemVendaDTO;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;

public class DadosVendaFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        modelValido();
    }

    private void modelValido() {
        Fixture.of(DadosVenda.class).addTemplate(VALIDO, new Rule() {{
            add("orderNumber", "101628208632");
            add("externalOrderNumber", "100423672693-1");
            add("customer", one(Customer.class, VALIDO));
            add("products", has(1).of(Product.class, VALIDO));
        }});

        Fixture.of(Customer.class).addTemplate(VALIDO, new Rule() {{
            add("id", "123456");
            add("name", "Givaldo Santos Vasconcelos");
            add("document", "70420816097");
            add("documentType", "CPF");
            add("personType", "F");
            add("address", "Travessa Francisco Vieira");
            add("addressNumber", "11");
            add("addressComplement", "Apto 405");
            add("district", "Trapiche da Barra");
            add("city", "Maceió");
            add("state", "AL");
            add("country", "BR");
            add("zipCode", "57010460");
            add("ibgeCode", "7162435");
            add("phoneNumber", "(82) 36774-7713");
            add("email", "givaldo.santos.vasconcelos@gmail.com");
        }});

        Fixture.of(Product.class).addTemplate(VALIDO, new Rule() {{
            add("sku", 324226428L);
            add("amount", 3L);
            add("value", new BigDecimal(56.91));
            add("icmsValue", new BigDecimal(38));
            add("pisValue", new BigDecimal(12));
            add("difaulValue", new BigDecimal(9));
            add("fcpIcmsValue", new BigDecimal(58));
        }});
    }
}
