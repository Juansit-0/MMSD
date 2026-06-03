package com.montaniamargarita.dto.request;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.List;

/**
 * Request para crear un pedido en una mesa.
 * Contiene la lista de items (producto + cantidad).
 */
public record CrearPedidoRequest(
        @NotBlank String mesaId,
        @NotNull @Size(min = 1, message = "Debe incluir al menos un item") @Valid List<ItemRequest> items) {

    /** Línea de pedido (producto + cantidad). */
    public record ItemRequest(
            @NotBlank String productoId,
            @Min(value = 1, message = "La cantidad debe ser mayor que cero") int cantidad) {
    }
}
