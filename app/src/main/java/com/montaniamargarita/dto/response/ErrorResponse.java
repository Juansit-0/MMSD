package com.montaniamargarita.dto.response;

import java.time.Instant;
import java.util.List;

/**
 * Formato uniforme de respuesta de error.
 * Inspirado en RFC 7807 Problem Details.
 */
public record ErrorResponse(
        Instant timestamp,
        int status,
        String code,
        String message,
        String path,
        List<FieldError> validationErrors) {

    /** Detalle por campo cuando la validación de Bean Validation falla. */
    public record FieldError(String campo, String mensaje) {
    }
}
