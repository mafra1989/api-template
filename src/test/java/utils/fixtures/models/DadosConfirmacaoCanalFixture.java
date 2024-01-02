package utils.fixtures.models;

import br.com.renner.domain.models.DadosConfirmacaoCanal;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

public class DadosConfirmacaoCanalFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        modelValido();
    }

    private void modelValido() {
        Fixture.of(DadosConfirmacaoCanal.class).addTemplate(VALIDO, new Rule() {{
            add("numeroPedido", "101628208632");
            add("numeroOrdemExterno", "100423672693-1");
            add("chaveNFE", "43210392754738001134550040000159551330237069");
            add("numeroNota", "0237069");
            add("dataEmissao", "2022-11-11T15:38:00.012");
            add("pdf", "NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAyMzcwNjk=");
            add("status", "PROCESSADO");
        }});
    }
}
