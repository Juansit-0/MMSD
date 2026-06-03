package com.montaniamargarita.exception;

import com.montaniamargarita.model.enums.EstadoPedido;
import org.springframework.http.HttpStatus;

/** Se lanza al intentar modificar un pedido que no está en ABIERTO ni EN_CURSO. */
public class PedidoNoModificableException extends DomainException {

    public PedidoNoModificableException(EstadoPedido estado) {
        super("PEDIDO_NO_MODIFICABLE",
                HttpStatus.CONFLICT,
                "El pedido no puede modificarse en estado " + estado);
    }
}
