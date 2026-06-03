package com.montaniamargarita.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CancelarPedidoRequest(
        @NotBlank @Size(min = 5, max = 200) String razon) {
}
