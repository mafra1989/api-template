package br.com.renner.infrastructure.adapters.output.rest.proxy.matriztributaria;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(url = "${spring.kafka.uri.parceiro.tributario}", name = "apitributo")
public interface MatrizTributariaServerProxy {

    @GetMapping("/tributo")
    ResponseEntity<MatrizTributariaResponseEntity> obterTributo(@RequestParam("sku") Long sku);
}
