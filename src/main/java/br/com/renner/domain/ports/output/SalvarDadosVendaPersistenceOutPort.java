package br.com.renner.domain.ports.output;

import br.com.renner.domain.models.DadosConfirmacaoCanal;
import br.com.renner.domain.models.venda.AutorizacaoVenda;

public interface SalvarDadosVendaPersistenceOutPort {

    void salvarDadosVenda(AutorizacaoVenda autorizacaoVenda,
                          DadosConfirmacaoCanal dadosConfirmacaoCanal,
                          String motivo);
}
