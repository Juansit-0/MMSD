package com.montaniamargarita.exception;

import org.springframework.http.HttpStatus;

/**
 * Excepción base para errores de dominio del sistema.
 * Cada subclase define su código de error y su HTTP status.
 */
public abstract class DomainException extends RuntimeException {

    private final String code;
    private final HttpStatus status;

    protected DomainException(String code, HttpStatus status, String mensaje) {
        super(mensaje);
        this.code = code;
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
