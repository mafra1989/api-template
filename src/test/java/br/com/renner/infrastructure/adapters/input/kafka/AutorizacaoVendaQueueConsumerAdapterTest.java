package br.com.renner.infrastructure.adapters.input.kafka;

import br.com.renner.application.venda.ProcessarAutorizacaoVendaUseCase;
import br.com.renner.infrastructure.adapters.input.kafka.AutorizacaoVendaQueueConsumerAdapter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@ExtendWith(MockitoExtension.class)
public class AutorizacaoVendaQueueConsumerAdapterTest {

    @InjectMocks
    private AutorizacaoVendaQueueConsumerAdapter autorizacaoVendaQueueConsumerAdapter;

    @Mock
    private ObjectMapper objectMapper;

    @Mock
    private ProcessarAutorizacaoVendaUseCase processarAutorizacaoVendaUseCase;

    @Test
    public void deveProcessarAutorizacaoVendaComSucesso() {
        // given
        ConsumerRecord<String, String> payload = new ConsumerRecord<>("topic", 0, 123L,
                "key", "value");

        // then
        assertDoesNotThrow(() -> {
            autorizacaoVendaQueueConsumerAdapter.processarAutorizacaoVenda(payload);
        });
    }

}
