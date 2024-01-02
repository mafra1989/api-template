package br.com.renner.infrastructure.configurations;

import br.com.renner.application.mapper.AutorizacaoVendaInputMapper;
import br.com.renner.application.venda.AutorizarVendaUseCase;
import br.com.renner.application.venda.ProcessarAutorizacaoVendaUseCase;
import br.com.renner.domain.ports.input.ValidacoesInPort;
import br.com.renner.domain.ports.output.AutorizacaoVendaQueueOutPort;
import br.com.renner.domain.ports.output.BuscarMatrizTributariaRestOutPort;
import br.com.renner.domain.ports.output.EnviarDadosVendaRestOutPort;
import br.com.renner.domain.ports.output.SalvarDadosVendaPersistenceOutPort;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Objects;

@Configuration
public class UseCaseConfig {

    private final AutorizacaoVendaInputMapper mapper;
    private final ValidacoesInPort validacoesInPort;
    private final AutorizacaoVendaQueueOutPort queueOutPort;
    private final BuscarMatrizTributariaRestOutPort matrizTributariaOutPort;
    private final EnviarDadosVendaRestOutPort enviarDadosVendaRestOutPort;
    private final SalvarDadosVendaPersistenceOutPort salvarDadosVendaPersistenceOutPort;

    public UseCaseConfig(final AutorizacaoVendaInputMapper mapper,
                         final ValidacoesInPort validacoesInPort,
                         final AutorizacaoVendaQueueOutPort queueOutPort,
                         final BuscarMatrizTributariaRestOutPort matrizTributariaOutPort,
                         final EnviarDadosVendaRestOutPort enviarDadosVendaRestOutPort,
                         final SalvarDadosVendaPersistenceOutPort salvarDadosVendaPersistenceOutPort) {
        this.mapper = Objects.requireNonNull(mapper);
        this.validacoesInPort = Objects.requireNonNull(validacoesInPort);
        this.queueOutPort = Objects.requireNonNull(queueOutPort);
        this.matrizTributariaOutPort = Objects.requireNonNull(matrizTributariaOutPort);
        this.enviarDadosVendaRestOutPort = Objects.requireNonNull(enviarDadosVendaRestOutPort);
        this.salvarDadosVendaPersistenceOutPort = Objects.requireNonNull(salvarDadosVendaPersistenceOutPort);
    }

    @Bean
    public AutorizarVendaUseCase autorizarVendaUseCase() {
        return new AutorizarVendaUseCase(mapper, validacoesInPort, queueOutPort);
    }

    @Bean
    public ProcessarAutorizacaoVendaUseCase processarAutorizacaoVendaUseCase() {
        return new ProcessarAutorizacaoVendaUseCase(mapper, matrizTributariaOutPort, enviarDadosVendaRestOutPort, salvarDadosVendaPersistenceOutPort);
    }
}
