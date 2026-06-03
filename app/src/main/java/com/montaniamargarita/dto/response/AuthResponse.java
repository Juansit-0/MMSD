package com.montaniamargarita.dto.response;

import com.montaniamargarita.model.enums.Rol;

/**
 * Respuesta de login. Incluye el JWT firmado y el rol del usuario.
 */
public record AuthResponse(
        String token,
        Rol rol,
        long expiraEn) {
}
