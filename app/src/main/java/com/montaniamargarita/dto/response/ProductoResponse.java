package com.montaniamargarita.dto.response;

import java.math.BigDecimal;

public record ProductoResponse(
        String id,
        String nombre,
        String categoriaId,
        BigDecimal precio,
        boolean activo) {
}
