package br.com.renner.infrastructure.adapters.output.rest.adapter;


import br.com.renner.domain.exception.ExternalAPICallerFeignException;
import br.com.renner.domain.models.DadosConfirmacaoCanal;
import br.com.renner.domain.models.sefaz.DadosVenda;
import br.com.renner.infrastructure.adapters.output.rest.mappers.DadosVendaOutputMapper;
import br.com.renner.infrastructure.adapters.output.rest.proxy.canal.ConfirmacaoCanalServerProxy;
import br.com.renner.infrastructure.adapters.output.rest.proxy.sefaz.DadosVendaResponseEntity;
import br.com.renner.infrastructure.adapters.output.rest.proxy.sefaz.DadosVendaServerProxy;
import br.com.renner.infrastructure.configurations.ProfileConfiguration;
import br.com.six2six.fixturefactory.Fixture;
import feign.FeignException;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import utils.FixtureLoader;
import utils.fixtures.models.DadosConfirmacaoCanalFixture;
import utils.fixtures.models.DadosVendaFixture;
import utils.fixtures.entities.DadosVendaResponseEntityFixture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class EnviarDadosVendaRestAdapterTest {

    @InjectMocks
    private EnviarDadosVendaRestAdapter enviarDadosVendaRestAdapter;

    @Mock
    private ProfileConfiguration profileConfiguration;

    @Spy
    private DadosVendaOutputMapper outputMapper = Mappers.getMapper(DadosVendaOutputMapper.class);

    @Mock
    private DadosVendaServerProxy dadosVendaServerProxy;

    @Mock
    private ConfirmacaoCanalServerProxy confirmacaoCanalServerProxy;

    @BeforeAll
    public static void setupScenarios() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    public void deveEnviarDadosParaSefazComSucesso() {
        // given
        DadosVenda dadosVendaMock = Fixture.from(DadosVenda.class).gimme(DadosVendaFixture.VALIDO);
        DadosVendaResponseEntity dadosVendaResponseEntityMock = Fixture.from(DadosVendaResponseEntity.class).gimme(DadosVendaResponseEntityFixture.VALIDO);
        ResponseEntity<DadosVendaResponseEntity> responseEntity = new ResponseEntity<>(
                dadosVendaResponseEntityMock,
                new HttpHeaders(),
                HttpStatus.OK
        );

        // when
        when(profileConfiguration.getFeatureToggleExternalAPICaller()).thenReturn(true);
        when(dadosVendaServerProxy.enviarDados(any())).thenReturn(responseEntity);

        // then
        assertDoesNotThrow(() -> {
            enviarDadosVendaRestAdapter.enviarDadosParaSefaz(dadosVendaMock);
        });
    }

    @Test
    public void deveEnviarDadosParaCanalComSucesso() {
        // given
        DadosConfirmacaoCanal dadosConfirmacaoCanalMock = Fixture.from(DadosConfirmacaoCanal.class).gimme(DadosConfirmacaoCanalFixture.VALIDO);
        final String entity = "Venda " + dadosConfirmacaoCanalMock.getNumeroOrdemExterno() + " recebida com sucesso";

        ResponseEntity<String> responseEntity = new ResponseEntity<>(
                entity,
                new HttpHeaders(),
                HttpStatus.OK
        );

        // when
        when(profileConfiguration.getFeatureToggleExternalAPICaller()).thenReturn(true);
        when(confirmacaoCanalServerProxy.enviarDados(any())).thenReturn(responseEntity);

        // then
        assertDoesNotThrow(() -> {
            enviarDadosVendaRestAdapter.enviarDadosParaCanal(dadosConfirmacaoCanalMock);
        });
    }

    @Test
    public void deveLancarExternalAPICallerFeignExceptionQuandoEnviarDadosParaSefaz() {
        // given
        DadosVenda dadosVendaMock = Fixture.from(DadosVenda.class).gimme(DadosVendaFixture.VALIDO);
        final String expectedMessage = "Erro na chamada do serviço externo.";

        // when
        when(profileConfiguration.getFeatureToggleExternalAPICaller()).thenReturn(true);
        when(dadosVendaServerProxy.enviarDados(any())).thenThrow(FeignException.class);

        // then
        ExternalAPICallerFeignException thrown = assertThrows(ExternalAPICallerFeignException.class, () -> {
            enviarDadosVendaRestAdapter.enviarDadosParaSefaz(dadosVendaMock);
        });
        assertEquals(expectedMessage, thrown.getErrorMessage());
    }

    @Test
    public void deveLancarExternalAPICallerFeignExceptionEnviarDadosParaCanal() {
        // given
        DadosConfirmacaoCanal dadosConfirmacaoCanalMock = Fixture.from(DadosConfirmacaoCanal.class).gimme(DadosConfirmacaoCanalFixture.VALIDO);
        final String expectedMessage = "Erro na chamada do serviço externo.";

        // when
        when(profileConfiguration.getFeatureToggleExternalAPICaller()).thenReturn(true);
        when(confirmacaoCanalServerProxy.enviarDados(any())).thenThrow(FeignException.class);

        // then
        ExternalAPICallerFeignException thrown = assertThrows(ExternalAPICallerFeignException.class, () -> {
            enviarDadosVendaRestAdapter.enviarDadosParaCanal(dadosConfirmacaoCanalMock);
        });
        assertEquals(expectedMessage, thrown.getErrorMessage());
    }
}
