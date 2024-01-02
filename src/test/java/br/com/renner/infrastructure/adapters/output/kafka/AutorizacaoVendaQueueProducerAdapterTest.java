package br.com.renner.infrastructure.adapters.output.kafka;

import br.com.renner.domain.models.venda.AutorizacaoVenda;
import br.com.renner.infrastructure.adapters.output.kafka.AutorizacaoVendaQueueProducerAdapter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
public class AutorizacaoVendaQueueProducerAdapterTest {

    @InjectMocks
    private AutorizacaoVendaQueueProducerAdapter autorizacaoVendaQueueProducerAdapter;

    @Mock
    private KafkaTemplate<String, Object> kafkaTemplate;


    @Test
    public void deveEnviarAutorizacaoParaFilaComSucesso() {
        // given
        AutorizacaoVenda autorizacaoVendaMock = mock(AutorizacaoVenda.class);

        // then
        assertDoesNotThrow(() -> {
            autorizacaoVendaQueueProducerAdapter.enviarAutorizacaoParaFila(autorizacaoVendaMock);
        });
    }
}
