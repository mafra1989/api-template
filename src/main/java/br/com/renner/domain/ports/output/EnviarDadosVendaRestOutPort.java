package br.com.renner.domain.ports.output;

import br.com.renner.domain.models.sefaz.DadosConfirmacaoSefaz;
import br.com.renner.domain.models.DadosConfirmacaoCanal;
import br.com.renner.domain.models.sefaz.DadosVenda;

public interface EnviarDadosVendaRestOutPort {

    DadosConfirmacaoSefaz enviarDadosParaSefaz(DadosVenda dadosVenda);

    String enviarDadosParaCanal(DadosConfirmacaoCanal dadosConfirmacaoCanal);
}
