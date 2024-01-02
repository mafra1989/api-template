package utils.fixtures.models;

import br.com.renner.domain.models.venda.AutorizacaoVenda;
import br.com.renner.domain.models.venda.Cliente;
import br.com.renner.domain.models.venda.Item;
import br.com.renner.domain.models.venda.OrdemPedido;
import br.com.six2six.fixturefactory.Fixture;
import br.com.six2six.fixturefactory.Rule;
import br.com.six2six.fixturefactory.loader.TemplateLoader;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class AutorizacaoVendaFixture implements TemplateLoader {

    public static final String VALIDO = "VALIDO";

    @Override
    public void load() {
        modelValido();
    }

    private void modelValido() {
        Fixture.of(AutorizacaoVenda.class).addTemplate(VALIDO, new Rule() {{
            add("canal", "APP");
            add("empresa", "00002");
            add("loja", "0001");
            add("pdv", 501L);
            add("ordemPedido", one(OrdemPedido.class, VALIDO));
            add("cliente", one(Cliente.class, VALIDO));
            add("totalItens", 38744L);
            add("quantidadeItens", 6L);
            add("itens", has(1).of(Item.class, VALIDO));
        }});

        Fixture.of(OrdemPedido.class).addTemplate(VALIDO, new Rule() {{
            add("numeroPedido", "101628208632");
            add("numeroOrdemExterno", "201437286937-1");
            add("dataAutorizacao", LocalDateTime.parse("2022-11-11T15:37:56.194"));
        }});

        Fixture.of(Cliente.class).addTemplate(VALIDO, new Rule() {{
            add("id", "123456");
            add("nome", "Givaldo Santos Vasconcelos");
            add("documento", "70420816097");
            add("tipoDocumento", "CPF");
            add("tipoPessoa", "F");
            add("endereco", "Travessa Francisco Vieira");
            add("numeroEndereco", "11");
            add("complementoEndereco", "Apto 405");
            add("bairro", "Trapiche da Barra");
            add("cidade", "Maceió");
            add("estado", "AL");
            add("pais", "BR");
            add("cep", "57010460");
            add("codigoIbge", "7162435");
            add("telefone", "(82) 36774-7713");
            add("email", "givaldo.santos.vasconcelos@gmail.com");
        }});

        Fixture.of(Item.class).addTemplate(VALIDO, new Rule() {{
            add("sku", 324226428L);
            add("quantidade", 3L);
            add("valor", new BigDecimal(5691));
        }});
    }
}
