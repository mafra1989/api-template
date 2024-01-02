package br.com.renner.domain.exception;

public class ExternalAPICallerFeignException extends BusinessException {

    public ExternalAPICallerFeignException(String errorCode, String errorMessage) {
        super(errorCode, errorMessage);
    }
}