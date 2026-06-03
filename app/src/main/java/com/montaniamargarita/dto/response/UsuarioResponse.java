package com.montaniamargarita.dto.response;

import com.montaniamargarita.model.enums.Rol;

import java.time.Instant;

/**
 * Respuesta con datos públicos del usuario.
 * Nunca incluye el pinHash.
 */
public record UsuarioResponse(
        String id,
        String nombreCompleto,
        String usuario,
        Rol rol,
        boolean activo,
        Instant fechaCreacion) {
}
