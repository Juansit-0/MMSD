package com.montaniamargarita.dto.response;

import java.math.BigDecimal;

public record ItemPedidoResponse(
        String productoId,
        String nombre,
        BigDecimal precio,
        int cantidad,
        BigDecimal subtotal) {
}
