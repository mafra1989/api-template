package br.com.renner.application.venda;

import br.com.renner.application.mapper.AutorizacaoVendaInputMapper;
import br.com.renner.domain.exception.ExternalAPICallerFeignException;
import br.com.renner.domain.models.MatrizTributaria;
import br.com.renner.domain.models.sefaz.DadosConfirmacaoSefaz;
import br.com.renner.domain.ports.output.BuscarMatrizTributariaRestOutPort;
import br.com.renner.domain.ports.output.EnviarDadosVendaRestOutPort;
import br.com.renner.domain.ports.output.SalvarDadosVendaPersistenceOutPort;
import br.com.renner.infrastructure.adapters.input.rest.dtos.AutorizacaoVendaDTO;
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
import utils.fixtures.dtos.AutorizacaoVendaDTOFixture;
import utils.fixtures.models.DadosConfirmacaoSefazFixture;
import utils.fixtures.models.MatrizTributariaFixture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProcessarAutorizacaoVendaUseCaseTest {

    @InjectMocks
    private ProcessarAutorizacaoVendaUseCase processarAutorizacaoVendaUseCase;

    @Spy
    private AutorizacaoVendaInputMapper mapper = Mappers.getMapper(AutorizacaoVendaInputMapper.class);

    @Mock
    private BuscarMatrizTributariaRestOutPort matrizTributariaOutPort;

    @Mock
    private EnviarDadosVendaRestOutPort enviarDadosVendaRestOutPort;

    @Mock
    private SalvarDadosVendaPersistenceOutPort salvarDadosVendaPersistenceOutPort;

    @BeforeAll
    public static void setupScenarios() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    public void deveSalvarOsDadosDaAutorizacaoDeVendaNoBancoDeDadosComSucesso() {
        // given
        AutorizacaoVendaDTO inputMock = Fixture.from(AutorizacaoVendaDTO.class).gimme(AutorizacaoVendaDTOFixture.VALIDO);
        MatrizTributaria matrizTributariaMock = Fixture.from(MatrizTributaria.class).gimme(MatrizTributariaFixture.VALIDO);
        DadosConfirmacaoSefaz dadosConfirmacaoSefazMock = Fixture.from(DadosConfirmacaoSefaz.class).gimme(DadosConfirmacaoSefazFixture.VALIDO);

        // when
        when(matrizTributariaOutPort.buscarTributo(any())).thenReturn(matrizTributariaMock);
        when(enviarDadosVendaRestOutPort.enviarDadosParaSefaz(any())).thenReturn(dadosConfirmacaoSefazMock);

        // then
        assertDoesNotThrow(() -> {
            processarAutorizacaoVendaUseCase.execute(new ProcessarAutorizacaoVendaUseCase.Input(inputMock));
        });
    }

    @Test
    public void deveSalvarOsDadosDaAutorizacaoDeVendaNoBancoDeDadosQuandoLancarExternalAPICallerFeignExceptionAoEnviarDadosParaCanal() {
        // given
        AutorizacaoVendaDTO inputMock = Fixture.from(AutorizacaoVendaDTO.class).gimme(AutorizacaoVendaDTOFixture.VALIDO);
        MatrizTributaria matrizTributariaMock = Fixture.from(MatrizTributaria.class).gimme(MatrizTributariaFixture.VALIDO);
        DadosConfirmacaoSefaz dadosConfirmacaoSefazMock = Fixture.from(DadosConfirmacaoSefaz.class).gimme(DadosConfirmacaoSefazFixture.VALIDO);

        // when
        when(matrizTributariaOutPort.buscarTributo(any())).thenReturn(matrizTributariaMock);
        when(enviarDadosVendaRestOutPort.enviarDadosParaSefaz(any())).thenReturn(dadosConfirmacaoSefazMock);
        when(enviarDadosVendaRestOutPort.enviarDadosParaCanal(any())).thenThrow(ExternalAPICallerFeignException.class);

        // then
        assertDoesNotThrow(() -> {
            processarAutorizacaoVendaUseCase.execute(new ProcessarAutorizacaoVendaUseCase.Input(inputMock));
        });
    }
}
