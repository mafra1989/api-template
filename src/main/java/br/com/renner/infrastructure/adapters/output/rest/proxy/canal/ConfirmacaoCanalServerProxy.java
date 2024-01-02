package br.com.renner.infrastructure.adapters.output.rest.proxy.canal;

import br.com.renner.domain.models.DadosConfirmacaoCanal;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${spring.kafka.uri.parceiro.canal}", name = "apicanal")
public interface ConfirmacaoCanalServerProxy {

    @PostMapping("/callback-venda")
    ResponseEntity<String> enviarDados(@RequestBody DadosConfirmacaoCanal dadosConfirmacaoCanal);
}
