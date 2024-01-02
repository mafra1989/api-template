package br.com.renner.infrastructure.adapters.input.rest.controllers;


import br.com.renner.application.mapper.AutorizacaoVendaInputMapper;
import br.com.renner.application.venda.AutorizarVendaUseCase;
import br.com.renner.infrastructure.adapters.input.rest.dtos.AutorizacaoVendaDTO;
import br.com.renner.infrastructure.adapters.input.rest.dtos.AutorizacaoVendaResponseDTO;
import br.com.renner.infrastructure.adapters.input.rest.exception.enumarator.MensagenInfraestruturaEnum;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
public class VendaController {

    private final AutorizarVendaUseCase autorizarVendaUseCase;

    public VendaController(final AutorizarVendaUseCase autorizarVendaUseCase, AutorizacaoVendaInputMapper mapper) {
        this.autorizarVendaUseCase = Objects.requireNonNull(autorizarVendaUseCase);
    }

    @PostMapping("/autorizar-venda")
    public ResponseEntity<?> autorizarVenda(@RequestBody AutorizacaoVendaDTO dto) {

        autorizarVendaUseCase.execute(new AutorizarVendaUseCase.Input(dto));

        return ResponseEntity.status(HttpStatus.CREATED).body(new AutorizacaoVendaResponseDTO(MensagenInfraestruturaEnum.EM_PROCESSAMENTO.getMensagem()));
    }
}
