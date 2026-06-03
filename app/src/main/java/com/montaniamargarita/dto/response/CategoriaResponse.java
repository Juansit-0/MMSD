package com.montaniamargarita.dto.response;

public record CategoriaResponse(
        String id,
        String nombre,
        String descripcion,
        boolean activo) {
}
