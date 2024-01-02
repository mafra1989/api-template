package br.com.renner.infrastructure.adapters.output.persistence.adapter;

import br.com.renner.domain.models.DadosConfirmacaoCanal;
import br.com.renner.domain.models.sefaz.DadosVenda;
import br.com.renner.domain.models.venda.AutorizacaoVenda;
import br.com.renner.domain.ports.output.SalvarDadosVendaPersistenceOutPort;
import br.com.renner.infrastructure.adapters.output.persistence.entity.DadosVendaEntity;
import br.com.renner.infrastructure.adapters.output.persistence.repository.DadosVendaRepository;
import br.com.renner.infrastructure.adapters.output.rest.mappers.DadosVendaOutputMapper;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Objects;

// Interface Adapter
@Component
public class SalvaDadosVendaPersistenceAdapter implements SalvarDadosVendaPersistenceOutPort {

    private final DadosVendaOutputMapper outputMapper;
    private final DadosVendaRepository dadosVendaRepository;

    public SalvaDadosVendaPersistenceAdapter(final DadosVendaOutputMapper outputMapper,
                                             final DadosVendaRepository dadosVendaRepository) {
        this.outputMapper = Objects.requireNonNull(outputMapper);
        this.dadosVendaRepository = Objects.requireNonNull(dadosVendaRepository);
    }

    @Override
    public void salvarDadosVenda(AutorizacaoVenda autorizacaoVenda,
                                 DadosConfirmacaoCanal dadosConfirmacaoCanal,
                                 String motivo) {

        DadosVendaEntity entity = outputMapper.toEntity(autorizacaoVenda, dadosConfirmacaoCanal);
        entity.setDataAtualizacao(LocalDateTime.now());
        entity.setDataRequisicao(LocalDateTime.now());
        entity.setMotivo(motivo);

        dadosVendaRepository.save(entity);
    }
}
