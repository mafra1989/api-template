package br.com.renner.infrastructure.adapters.input.rest.exception.handler;

import br.com.renner.domain.exception.ValidationException;
import br.com.renner.infrastructure.adapters.input.rest.exception.enumarator.MensagenInfraestruturaEnum;
import br.com.renner.infrastructure.adapters.input.rest.exception.erro.ApiErroResponse;
import br.com.renner.infrastructure.adapters.input.rest.exception.erro.ApiSubErroResponse;
import br.com.renner.infrastructure.adapters.input.rest.exception.erro.SubErroNegocio;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Objects;
import java.util.Optional;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class BusinessExceptionHandlerTest {

    @InjectMocks
    private BusinessExceptionHandler exceptionHandler;

    @Test
    @DisplayName("Deve montar validation exception com sucesso")
    void deveMontarValidationExceptionComSucesso() {

        ValidationException ex = new ValidationException("RN001", "canal não deve estar em branco");
        ApiErroResponse response = exceptionHandler.handleValidationException(ex);

        HttpStatus statusHttpEsperado = HttpStatus.BAD_REQUEST;
        String mensagemEsperada = MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem();
        String codigoEsperado = ex.getErrorCode();
        String mensagemErroEsperado = ex.getErrorMessage();

        Optional<ApiSubErroResponse> erro = response.getErrors().stream().findFirst();
        if(erro.isPresent()) {
            assertEquals(codigoEsperado, ((SubErroNegocio) erro.get()).getErrorCode());
            assertEquals(mensagemErroEsperado, ((SubErroNegocio) erro.get()).getErrorMessage());
        }

        assertEquals(statusHttpEsperado, response.getStatus());
        assertEquals(mensagemEsperada, Objects.requireNonNull(response.getMessage()));
    }

    @Test
    @DisplayName("Deve montar invalid format exception com sucesso")
    void deveMontarInvalidFormatExceptionComSucesso() {
        InvalidFormatException ex = Mockito.mock(InvalidFormatException.class);

        ApiErroResponse response = exceptionHandler.handleInvalidFormatException(ex);

        String mensagemEsperada = MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem();
        HttpStatus statusHttpEsperado = HttpStatus.BAD_REQUEST;

        assertNotNull(response);
        assertEquals(mensagemEsperada, response.getMessage());
        assertEquals(statusHttpEsperado, response.getStatus());
    }

    @Test
    @DisplayName("Deve montar datetime parse exception com sucesso")
    void deveMontarDateTimeParseExceptionComSucesso() {
        InvalidFormatException ex = Mockito.mock(InvalidFormatException.class);

        ApiErroResponse response = exceptionHandler.handleInvalidFormatException(ex);

        String mensagemEsperada = MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem();
        HttpStatus statusHttpEsperado = HttpStatus.BAD_REQUEST;

        assertNotNull(response);
        assertEquals(mensagemEsperada, response.getMessage());
        assertEquals(statusHttpEsperado, response.getStatus());
    }

    @Test
    @DisplayName("Deve montar JsonMappingException com sucesso")
    void deveMontarInvalidJsonMappingComSucesso() {
        JsonMappingException ex = Mockito.mock(JsonMappingException.class);

        ApiErroResponse response = exceptionHandler.handleJsonMappingException(ex);

        String mensagemEsperada = MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem();
        HttpStatus statusHttpEsperado = HttpStatus.BAD_REQUEST;

        assertNotNull(response);
        assertEquals(mensagemEsperada, response.getMessage());
        assertEquals(statusHttpEsperado, response.getStatus());
    }
}
