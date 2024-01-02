package br.com.renner.infrastructure.adapters.output.persistence.adapter;

import br.com.renner.domain.models.DadosConfirmacaoCanal;
import br.com.renner.domain.models.venda.AutorizacaoVenda;
import br.com.renner.infrastructure.adapters.output.persistence.repository.DadosVendaRepository;
import br.com.renner.infrastructure.adapters.output.rest.mappers.DadosVendaOutputMapper;
import br.com.six2six.fixturefactory.Fixture;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import utils.FixtureLoader;
import utils.fixtures.models.AutorizacaoVendaFixture;
import utils.fixtures.models.DadosConfirmacaoCanalFixture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class SalvaDadosVendaPersistenceAdapterTest {

    @InjectMocks
    private SalvaDadosVendaPersistenceAdapter salvaDadosVendaPersistenceAdapter;

    @Spy
    private DadosVendaOutputMapper outputMapper = Mappers.getMapper(DadosVendaOutputMapper.class);

    @Mock
    private DadosVendaRepository dadosVendaRepository;

    @BeforeAll
    public static void setupScenarios() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    public void deveEnviarAutorizacaoParaFilaComSucesso() {
        // given
        AutorizacaoVenda autorizacaoVendaMock = Fixture.from(AutorizacaoVenda.class).gimme(AutorizacaoVendaFixture.VALIDO);
        DadosConfirmacaoCanal dadosConfirmacaoCanalMock = Fixture.from(DadosConfirmacaoCanal.class).gimme(DadosConfirmacaoCanalFixture.VALIDO);
        final String motivoMock = "Venda " + autorizacaoVendaMock.getOrdemPedido().getNumeroOrdemExterno() + " recebida com erro";

        // then
        assertDoesNotThrow(() -> {
            salvaDadosVendaPersistenceAdapter.salvarDadosVenda(autorizacaoVendaMock, dadosConfirmacaoCanalMock, motivoMock);
        });
    }
}
