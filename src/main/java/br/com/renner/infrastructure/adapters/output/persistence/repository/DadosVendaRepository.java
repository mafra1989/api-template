package br.com.renner.infrastructure.adapters.output.persistence.repository;

import br.com.renner.infrastructure.adapters.output.persistence.entity.DadosVendaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DadosVendaRepository extends JpaRepository<DadosVendaEntity, Long> {
}
