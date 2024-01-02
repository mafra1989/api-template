package br.com.renner.infrastructure.adapters.input.rest.exception.erro;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ApiErroResponse {

    private String message;

    private HttpStatus status;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy - hh:mm:ss")
    private LocalDateTime timeStamp = LocalDateTime.now();

    private List<ApiSubErroResponse> errors;

    private String developerMessage;

    public ApiErroResponse(String message, HttpStatus status, Throwable ex) {
        this.message = message;
        this.status = status;
        this.developerMessage = ex.getLocalizedMessage();
    }

    private void addSubErro(ApiSubErroResponse apiSubErroResponse) {
        if (errors == null){
            errors = new ArrayList<>();
        }
        errors.add(apiSubErroResponse);
    }

    public void addErroNegocio(String codigo, String message) {
        addSubErro(new SubErroNegocio(codigo, message));
    }

}
