package com.montaniamargarita.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CrearCategoriaRequest(
        @NotBlank @Size(max = 50) String nombre,
        @Size(max = 200) String descripcion) {
}
