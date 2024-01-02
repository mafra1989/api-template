package utils.fixtures.entities;

import br.com.renner.infrastructure.adapters.output.rest.proxy.sefaz.DadosVendaResponseEntity;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DadosVendaResponseEntityFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        entityValido();
    }

    private void entityValido() {
        Fixture.of(DadosVendaResponseEntity.class).addTemplate(VALIDO, new Rule() {{
            add("nfeKey", "43210392754738001134550040000159551330237069");
            add("invoiceNumber", "0237069");
            add("issuanceDate", "2022-11-11T15:38:00.012");
            add("invoice", "NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAyMzcwNjk=\"");
        }});
    }
}
