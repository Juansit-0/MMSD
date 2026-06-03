package com.montaniamargarita.dto.request;

import com.montaniamargarita.model.enums.Rol;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record CrearUsuarioRequest(
        @NotBlank @Size(max = 100) String nombreCompleto,
        @NotBlank @Size(min = 4, max = 30) String usuario,
        @NotBlank @Pattern(regexp = "^\\d{4,6}$", message = "El PIN debe tener entre 4 y 6 dígitos numéricos") String pin,
        @NotNull Rol rol) {
}
