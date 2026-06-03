package com.montaniamargarita.model.enums;

/**
 * Ciclo de vida de un pedido.
 * Solo enumera los estados; no contiene operaciones.
 */
public enum EstadoPedido {
    ABIERTO,
    EN_CURSO,
    CERRADO,
    FACTURADO,
    CANCELADO
}
