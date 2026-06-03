package com.montaniamargarita.exception;

import com.montaniamargarita.model.enums.EstadoPedido;
import org.springframework.http.HttpStatus;

/** Se lanza al intentar facturar un pedido que no está en estado CERRADO. */
public class PedidoNoFacturableException extends DomainException {

    public PedidoNoFacturableException(EstadoPedido estado) {
        super("PEDIDO_NO_FACTURABLE",
                HttpStatus.CONFLICT,
                "El pedido debe estar CERRADO para facturarse. Estado actual: " + estado);
    }
}
