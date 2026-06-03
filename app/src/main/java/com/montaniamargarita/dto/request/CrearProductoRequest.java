package com.montaniamargarita.dto.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

public record CrearProductoRequest(
        @NotBlank @Size(max = 80) String nombre,
        @NotBlank String categoriaId,
        @NotNull @DecimalMin(value = "0.01", message = "El precio debe ser positivo") BigDecimal precio) {
}
