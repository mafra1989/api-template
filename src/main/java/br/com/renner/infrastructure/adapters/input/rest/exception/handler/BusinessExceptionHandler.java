package br.com.renner.infrastructure.adapters.input.rest.exception.handler;

import br.com.renner.domain.exception.ValidationException;
import br.com.renner.infrastructure.adapters.input.rest.exception.enumarator.MensagenInfraestruturaEnum;
import br.com.renner.infrastructure.adapters.input.rest.exception.erro.ApiErroResponse;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class BusinessExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErroResponse handleValidationException(ValidationException ex){

        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);
        var messages = ex.getErrorMessage().split("/");
        for (String message: messages) {
            error.addErroNegocio(ex.getErrorCode(), message);
        }

        return error;
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErroResponse handleInvalidFormatException(InvalidFormatException  ex) {
        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);

        error.addErroNegocio("40", MensagenInfraestruturaEnum.VALOR_INVALIDO.getMensagem());

        return error;
    }

    @ExceptionHandler(DateTimeParseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErroResponse handleDateTimeParseException(DateTimeParseException ex) {
        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);

        error.addErroNegocio("50", MensagenInfraestruturaEnum.DATA_INVALIDA.getMensagem());

        return error;
    }

    @ExceptionHandler(JsonMappingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErroResponse handleJsonMappingException(JsonMappingException ex) {
        ApiErroResponse error = new ApiErroResponse(MensagenInfraestruturaEnum.FALHA_PROCESSAMENTO.getMensagem(), HttpStatus.BAD_REQUEST, ex);

        error.addErroNegocio("60", MensagenInfraestruturaEnum.ERRO_JSON.getMensagem());

        return error;
    }

}
