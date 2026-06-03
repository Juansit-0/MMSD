package com.montaniamargarita.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

/**
 * DTO de login. Recibe usuario + PIN.
 */
public record LoginRequest(
        @NotBlank @Size(min = 4, max = 30) String usuario,
        @NotBlank @Pattern(regexp = "^\\d{4,6}$", message = "El PIN debe tener entre 4 y 6 dígitos numéricos") String pin) {
}
