package br.com.renner.domain.exception;

public class ValidationException extends BusinessException {

    public ValidationException(String code, String message) {
        super(code, message);
    }

}