package br.com.renner.infrastructure.adapters.output.rest.adapter;

import br.com.renner.domain.exception.ExternalAPICallerFeignException;
import br.com.renner.infrastructure.adapters.output.rest.mappers.MatrizTributariaOutputMapper;
import br.com.renner.infrastructure.adapters.output.rest.proxy.matriztributaria.MatrizTributariaResponseEntity;
import br.com.renner.infrastructure.adapters.output.rest.proxy.matriztributaria.MatrizTributariaServerProxy;
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
import utils.fixtures.entities.MatrizTributariaResponseEntityFixture;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class BuscarMatrizTributariaRestAdapterTest {

    @InjectMocks
    private BuscarMatrizTributariaRestAdapter buscarMatrizTributariaRestAdapter;

    @Mock
    private ProfileConfiguration profileConfiguration;

    @Spy
    private MatrizTributariaOutputMapper outputMapper = Mappers.getMapper(MatrizTributariaOutputMapper.class);

    @Mock
    private MatrizTributariaServerProxy matrizTributariaServerProxy;

    @BeforeAll
    public static void setupScenarios() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    public void deveBuscarTributoComSucesso() {
        // given
        final Long sku = 324226428L;
        MatrizTributariaResponseEntity matrizTributariaResponseEntityMock = Fixture.from(MatrizTributariaResponseEntity.class).gimme(MatrizTributariaResponseEntityFixture.VALIDO);
        ResponseEntity<MatrizTributariaResponseEntity> responseEntity = new ResponseEntity<>(
                matrizTributariaResponseEntityMock,
                new HttpHeaders(),
                HttpStatus.OK
        );

        // when
        when(profileConfiguration.getFeatureToggleExternalAPICaller()).thenReturn(true);
        when(matrizTributariaServerProxy.obterTributo(any())).thenReturn(responseEntity);

        // then
        assertDoesNotThrow(() -> {
            buscarMatrizTributariaRestAdapter.buscarTributo(sku);
        });
    }

    @Test
    public void deveLancarExternalAPICallerFeignException() {
        // given
        final Long sku = 324226428L;
        final String expectedMessage = "Erro na chamada do serviço externo.";

        // when
        when(profileConfiguration.getFeatureToggleExternalAPICaller()).thenReturn(true);
        when(matrizTributariaServerProxy.obterTributo(any())).thenThrow(FeignException.class);

        // then
        ExternalAPICallerFeignException thrown = assertThrows(ExternalAPICallerFeignException.class, () -> {
            buscarMatrizTributariaRestAdapter.buscarTributo(sku);
        });
        assertEquals(expectedMessage, thrown.getErrorMessage());
    }

}
