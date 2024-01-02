package br.com.renner.infrastructure.adapters.output.kafka;

import br.com.renner.domain.ports.output.AutorizacaoVendaQueueOutPort;
import br.com.renner.domain.models.venda.AutorizacaoVenda;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Objects;

// Interface Adapter
@Component
public class AutorizacaoVendaQueueProducerAdapter implements AutorizacaoVendaQueueOutPort {

    private final KafkaTemplate<String, Object> kafkaTemplate;

    @Value("${spring.kafka.topico.autorizacao-venda}")
    private String topicoAutorizacaoVenda;

    public AutorizacaoVendaQueueProducerAdapter(final KafkaTemplate<String, Object> kafkaTemplate) {
        this.kafkaTemplate = Objects.requireNonNull(kafkaTemplate);
    }

    @Override
    public void enviarAutorizacaoParaFila(AutorizacaoVenda autorizacaoVenda) {
        this.kafkaTemplate.send(topicoAutorizacaoVenda, autorizacaoVenda);
    }
}
