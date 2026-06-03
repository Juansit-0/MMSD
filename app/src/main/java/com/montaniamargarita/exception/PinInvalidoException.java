package com.montaniamargarita.exception;

import org.springframework.http.HttpStatus;

/** Credenciales de login inválidas (usuario inexistente o PIN incorrecto). */
public class PinInvalidoException extends DomainException {

    public PinInvalidoException() {
        super("CREDENCIALES_INVALIDAS",
                HttpStatus.UNAUTHORIZED,
                "Credenciales inválidas");
    }
}
