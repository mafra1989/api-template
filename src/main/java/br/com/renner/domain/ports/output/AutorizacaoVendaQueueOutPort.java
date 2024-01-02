package br.com.renner.domain.ports.output;

import br.com.renner.domain.models.venda.AutorizacaoVenda;

public interface AutorizacaoVendaQueueOutPort {

    void enviarAutorizacaoParaFila(AutorizacaoVenda autorizacaoVenda);
}
