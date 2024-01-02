package br.com.renner.infrastructure.adapters.output.rest.proxy.sefaz;

import br.com.renner.domain.models.sefaz.DadosVenda;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(url = "${spring.kafka.uri.parceiro.sefaz}", name = "apisefaz")
public interface DadosVendaServerProxy {

    @PostMapping("/authorize")
    ResponseEntity<DadosVendaResponseEntity> enviarDados(@RequestBody DadosVenda dadosVenda);
}
