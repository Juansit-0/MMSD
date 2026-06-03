package com.montaniamargarita.exception;

import org.springframework.http.HttpStatus;

/** Se lanza cuando se intenta acceder a un recurso inexistente. */
public class RecursoNoEncontradoException extends DomainException {

    public RecursoNoEncontradoException(String recurso, String id) {
        super("RECURSO_NO_ENCONTRADO",
                HttpStatus.NOT_FOUND,
                recurso + " con id " + id + " no encontrado");
    }
}
