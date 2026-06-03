package com.montaniamargarita.exception;

import org.springframework.http.HttpStatus;

/** Se lanza al intentar crear un usuario con nombre de usuario ya existente. */
public class UsuarioYaExisteException extends DomainException {

    public UsuarioYaExisteException(String usuario) {
        super("USUARIO_YA_EXISTE",
                HttpStatus.CONFLICT,
                "Ya existe un usuario con el nombre '" + usuario + "'");
    }
}
