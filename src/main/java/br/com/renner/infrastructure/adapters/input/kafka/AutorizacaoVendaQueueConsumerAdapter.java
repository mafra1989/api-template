package br.com.renner.infrastructure.adapters.input.kafka;

import br.com.renner.application.venda.ProcessarAutorizacaoVendaUseCase;
import br.com.renner.infrastructure.adapters.input.rest.dtos.AutorizacaoVendaDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.util.Objects;

// Interface Adapter
@Component
public class AutorizacaoVendaQueueConsumerAdapter {

    private final ObjectMapper objectMapper;
    private final ProcessarAutorizacaoVendaUseCase processarAutorizacaoVendaUseCase;

    public AutorizacaoVendaQueueConsumerAdapter(
            final ObjectMapper objectMapper,
            final ProcessarAutorizacaoVendaUseCase processarAutorizacaoVendaUseCase) {
        this.objectMapper = Objects.requireNonNull(objectMapper);
        this.processarAutorizacaoVendaUseCase = Objects.requireNonNull(processarAutorizacaoVendaUseCase);
    }

    @KafkaListener(topics = "${spring.kafka.topico.autorizacao-venda}")
    public void processarAutorizacaoVenda(ConsumerRecord<String, String> payload) throws JsonProcessingException {
        AutorizacaoVendaDTO payloadDto =
                this.objectMapper.readValue(payload.value(), AutorizacaoVendaDTO.class);

        processarAutorizacaoVendaUseCase
                .execute(new ProcessarAutorizacaoVendaUseCase.Input(payloadDto));
    }
}
