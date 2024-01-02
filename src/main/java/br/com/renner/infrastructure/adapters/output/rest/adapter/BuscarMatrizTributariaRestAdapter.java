package br.com.renner.infrastructure.adapters.output.rest.adapter;

import br.com.renner.domain.enumerators.MensagensNegociosEnum;
import br.com.renner.domain.exception.ExternalAPICallerFeignException;
import br.com.renner.domain.models.MatrizTributaria;
import br.com.renner.domain.ports.output.BuscarMatrizTributariaRestOutPort;
import br.com.renner.infrastructure.adapters.output.rest.mappers.MatrizTributariaOutputMapper;
import br.com.renner.infrastructure.adapters.output.rest.proxy.matriztributaria.MatrizTributariaResponseEntity;
import br.com.renner.infrastructure.adapters.output.rest.proxy.matriztributaria.MatrizTributariaServerProxy;
import br.com.renner.infrastructure.configurations.ProfileConfiguration;
import feign.FeignException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Objects;

// Interface Adapter
@Component
public class BuscarMatrizTributariaRestAdapter implements BuscarMatrizTributariaRestOutPort {

    private final ProfileConfiguration profileConfiguration;
    private final MatrizTributariaOutputMapper outputMapper;
    private final MatrizTributariaServerProxy matrizTributariaServerProxy;

    public BuscarMatrizTributariaRestAdapter(final ProfileConfiguration profileConfiguration,
                                             final MatrizTributariaOutputMapper outputMapper,
                                             final MatrizTributariaServerProxy matrizTributariaServerProxy) {
        this.profileConfiguration = Objects.requireNonNull(profileConfiguration);
        this.outputMapper = Objects.requireNonNull(outputMapper);
        this.matrizTributariaServerProxy = Objects.requireNonNull(matrizTributariaServerProxy);
    }

    @Override
    @Cacheable(cacheNames = {"buscarTributo"})
    @CircuitBreaker(name = "parceiro-circuitbreaker")
    public MatrizTributaria buscarTributo(Long sku) {
        MatrizTributariaResponseEntity entity = null;

        if(profileConfiguration.getFeatureToggleExternalAPICaller()) {
            ResponseEntity<MatrizTributariaResponseEntity> responseEntity = null;
            try {
                responseEntity = matrizTributariaServerProxy.obterTributo(sku);
            } catch (FeignException ex) {
                throw new ExternalAPICallerFeignException(MensagensNegociosEnum.ERRO_INTERNO.getCodigo(),
                        MensagensNegociosEnum.ERRO_INTERNO.getMensagem());
            }
            entity = responseEntity.getBody();

        } else {
            entity = MatrizTributariaResponseEntity.builder()
                    .sku(BigInteger.valueOf(324226428L))
                    .valorIcms(BigDecimal.valueOf(38L))
                    .valorPis(BigDecimal.valueOf(12))
                    .valorDifaul(BigDecimal.valueOf(9))
                    .valorFcpIcms(BigDecimal.valueOf(58)).build();
        }

        return outputMapper.toDomain(entity);
    }
}
