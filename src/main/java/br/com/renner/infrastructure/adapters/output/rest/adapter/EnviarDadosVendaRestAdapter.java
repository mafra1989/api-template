package br.com.renner.infrastructure.adapters.output.rest.adapter;

import br.com.renner.domain.enumerators.MensagensNegociosEnum;
import br.com.renner.domain.exception.ExternalAPICallerFeignException;
import br.com.renner.domain.models.DadosConfirmacaoCanal;
import br.com.renner.domain.models.sefaz.DadosConfirmacaoSefaz;
import br.com.renner.domain.models.sefaz.DadosVenda;
import br.com.renner.domain.ports.output.EnviarDadosVendaRestOutPort;
import br.com.renner.infrastructure.adapters.output.rest.mappers.DadosVendaOutputMapper;
import br.com.renner.infrastructure.adapters.output.rest.proxy.canal.ConfirmacaoCanalServerProxy;
import br.com.renner.infrastructure.adapters.output.rest.proxy.matriztributaria.MatrizTributariaResponseEntity;
import br.com.renner.infrastructure.adapters.output.rest.proxy.sefaz.DadosVendaResponseEntity;
import br.com.renner.infrastructure.adapters.output.rest.proxy.sefaz.DadosVendaServerProxy;
import br.com.renner.infrastructure.configurations.ProfileConfiguration;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

// Interface Adapter
@Component
public class EnviarDadosVendaRestAdapter implements EnviarDadosVendaRestOutPort {

    private final ProfileConfiguration profileConfiguration;
    private final DadosVendaOutputMapper outputMapper;
    private final DadosVendaServerProxy dadosVendaServerProxy;
    private final ConfirmacaoCanalServerProxy confirmacaoCanalServerProxy;

    public EnviarDadosVendaRestAdapter(final ProfileConfiguration profileConfiguration,
                                       final DadosVendaOutputMapper outputMapper,
                                       final DadosVendaServerProxy dadosVendaServerProxy,
                                       final ConfirmacaoCanalServerProxy confirmacaoCanalServerProxy) {
        this.profileConfiguration = Objects.requireNonNull(profileConfiguration);
        this.outputMapper = Objects.requireNonNull(outputMapper);
        this.dadosVendaServerProxy = Objects.requireNonNull(dadosVendaServerProxy);
        this.confirmacaoCanalServerProxy = Objects.requireNonNull(confirmacaoCanalServerProxy);
    }

    @Override
    @CircuitBreaker(name = "parceiro-circuitbreaker")
    public DadosConfirmacaoSefaz enviarDadosParaSefaz(DadosVenda dadosVenda) {
        DadosVendaResponseEntity entity = null;

        if(profileConfiguration.getFeatureToggleExternalAPICaller()) {
            ResponseEntity<DadosVendaResponseEntity> responseEntity = null;
            try {
                responseEntity = dadosVendaServerProxy.enviarDados(dadosVenda);
            } catch (FeignException ex) {
                throw new ExternalAPICallerFeignException(MensagensNegociosEnum.ERRO_INTERNO.getCodigo(),
                        MensagensNegociosEnum.ERRO_INTERNO.getMensagem());
            }
            entity = responseEntity.getBody();

        } else {
            entity = DadosVendaResponseEntity.builder()
                    .nfeKey("43210392754738001134550040000159551330237069")
                    .invoiceNumber("0237069")
                    .issuanceDate("2022-11-11T15:38:00.012")
                    .invoice("NDMyMTAzOTI3NTQ3MzgwMDExMzQ1NTAwNDAwMDAxNTk1NTEzMzAyMzcwNjk=").build();
        }

        return outputMapper.toDomain(entity);
    }

    @Override
    @CircuitBreaker(name = "parceiro-circuitbreaker")
    public String enviarDadosParaCanal(DadosConfirmacaoCanal dadosConfirmacaoCanal) {
        String entity = null;

        if(profileConfiguration.getFeatureToggleExternalAPICaller()) {
            ResponseEntity<String> responseEntity = null;
            try {
                responseEntity = confirmacaoCanalServerProxy.enviarDados(dadosConfirmacaoCanal);
            } catch (FeignException ex) {
                throw new ExternalAPICallerFeignException(MensagensNegociosEnum.ERRO_INTERNO.getCodigo(),
                        MensagensNegociosEnum.ERRO_INTERNO.getMensagem());
            }
            entity = responseEntity.getBody();
        } else {
            entity = "Venda " + dadosConfirmacaoCanal.getNumeroOrdemExterno() + " recebida com sucesso";
        }

        return entity;
    }
}
