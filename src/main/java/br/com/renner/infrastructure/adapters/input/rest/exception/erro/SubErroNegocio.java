package br.com.renner.infrastructure.adapters.input.rest.exception.erro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class SubErroNegocio implements ApiSubErroResponse {

        private final String errorCode;
        private final String errorMessage;

}
