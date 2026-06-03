package com.montaniamargarita.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/**
 * Request para agregar o eliminar items de un pedido existente.
 * La operación se indica con un string ("AGREGAR" o "ELIMINAR").
 */
public record ModificarItemPedidoRequest(
        @NotNull Operacion operacion,
        @NotBlank String productoId,
        @Min(value = 1, message = "La cantidad debe ser mayor que cero") int cantidad) {

    public enum Operacion {
        AGREGAR,
        ELIMINAR
    }
}
