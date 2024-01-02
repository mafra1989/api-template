package utils.fixtures.models;

import br.com.renner.domain.models.sefaz.DadosConfirmacaoSefaz;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DadosConfirmacaoSefazFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        modelValido();
    }

    private void modelValido() {
        Fixture.of(DadosConfirmacaoSefaz.class).addTemplate(VALIDO, new Rule() {{
            add("nfeKey", "43210392754738001134550040000159551330237069");
            add("invoiceNumber", "0237069");
            add("issuanceDate", "2022-11-11T15:38:00.012");
            add("invoice", "NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAyMzcwNjk=");
        }});
    }
}
