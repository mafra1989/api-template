package br.com.renner.application.venda;

import br.com.renner.application.mapper.AutorizacaoVendaInputMapper;
import br.com.renner.domain.exception.ValidationException;
import br.com.renner.domain.ports.input.ValidacoesInPort;
import br.com.renner.domain.ports.output.AutorizacaoVendaQueueOutPort;
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

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AutorizarVendaUseCaseTest {

    @InjectMocks
    private AutorizarVendaUseCase autorizarVendaUseCase;

    @Spy
    private AutorizacaoVendaInputMapper mapper = Mappers.getMapper(AutorizacaoVendaInputMapper.class);

    @Mock
    private ValidacoesInPort validacoesInPort;

    @Mock
    private AutorizacaoVendaQueueOutPort queueOutPort;

    @BeforeAll
    public static void setupScenarios() {
        FixtureLoader.loadAllFixtures();
    }

    @Test
    public void deveEnviarAutorizacaoParaFilaComSucesso() {
        // given
        AutorizacaoVendaDTO inputMock = Fixture.from(AutorizacaoVendaDTO.class).gimme(AutorizacaoVendaDTOFixture.VALIDO);

        assertDoesNotThrow(() -> {
            autorizarVendaUseCase.execute(new AutorizarVendaUseCase.Input(inputMock));
        });
    }

    @Test
    public void deveLancarValidationExceptionQuandoHouverViolacaoDeContrato() {
        // given
        AutorizacaoVendaDTO inputMock = new AutorizacaoVendaDTO();
        final StringBuilder expectedMessages = new StringBuilder()
                .append("ordemPedido.dataAutorizacao não deve ser nulo/")
                .append("ordemPedido.numeroOrdemExterno não deve estar em branco/")
                .append("ordemPedido.numeroOrdemExterno não deve ser nulo/")
                .append("ordemPedido.numeroPedido não deve estar em branco/")
                .append("ordemPedido.numeroPedido não deve ser nulo/");

        // when
        when(validacoesInPort.execute(any())).thenReturn(
                Optional.of(new StringBuilder().append(expectedMessages)));

        // then
        ValidationException thrown = assertThrows(ValidationException.class, () -> {
            autorizarVendaUseCase.execute(new AutorizarVendaUseCase.Input(inputMock));
        });
        assertEquals(expectedMessages.toString(), thrown.getErrorMessage());
    }
}
