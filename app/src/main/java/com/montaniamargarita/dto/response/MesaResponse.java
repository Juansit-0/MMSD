package com.montaniamargarita.dto.response;

import com.montaniamargarita.model.enums.EstadoMesa;

public record MesaResponse(
        String id,
        int numero,
        EstadoMesa estado) {
}
