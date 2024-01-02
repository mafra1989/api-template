package br.com.renner.application.venda;

import br.com.renner.application.UseCase;
import br.com.renner.domain.exception.ValidationException;
import br.com.renner.application.mapper.AutorizacaoVendaInputMapper;
import br.com.renner.domain.models.venda.AutorizacaoVenda;
import br.com.renner.domain.ports.input.ValidacoesInPort;
import br.com.renner.domain.ports.output.AutorizacaoVendaQueueOutPort;
import br.com.renner.infrastructure.adapters.input.rest.dtos.AutorizacaoVendaDTO;

import java.util.Objects;
import java.util.Optional;

public class AutorizarVendaUseCase extends UseCase<AutorizarVendaUseCase.Input> {

    private final AutorizacaoVendaInputMapper mapper;
    private final ValidacoesInPort validacoesInPort;
    private final AutorizacaoVendaQueueOutPort queueOutPort;

    public AutorizarVendaUseCase(
            final AutorizacaoVendaInputMapper mapper,
            final ValidacoesInPort validacoesInPort,
            final AutorizacaoVendaQueueOutPort queueOutPort) {
        this.mapper = Objects.requireNonNull(mapper);
        this.validacoesInPort = Objects.requireNonNull(validacoesInPort);
        this.queueOutPort = Objects.requireNonNull(queueOutPort);
    }

    @Override
    public void execute(Input input) {

        AutorizacaoVenda autorizacaoVenda = mapper.toDomain(input.dto());

        Optional<StringBuilder> result = validacoesInPort.execute(autorizacaoVenda);
        if(result.isPresent()) {
            throw new ValidationException("RN001", result.get().toString());
        }

        queueOutPort.enviarAutorizacaoParaFila(autorizacaoVenda);
    }

    public record Input(AutorizacaoVendaDTO dto) {
    }
}
