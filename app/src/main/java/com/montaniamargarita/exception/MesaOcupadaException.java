package com.montaniamargarita.exception;

import org.springframework.http.HttpStatus;

/** Se lanza cuando se intenta crear un pedido en una mesa que ya tiene uno activo. */
public class MesaOcupadaException extends DomainException {

    public MesaOcupadaException() {
        super("MESA_OCUPADA",
                HttpStatus.CONFLICT,
                "La mesa solicitada ya tiene un pedido activo");
    }
}
