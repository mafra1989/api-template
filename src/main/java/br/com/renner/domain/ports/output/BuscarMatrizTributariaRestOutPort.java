package br.com.renner.domain.ports.output;

import br.com.renner.domain.models.MatrizTributaria;

public interface BuscarMatrizTributariaRestOutPort {

    MatrizTributaria buscarTributo(Long sku);
}
