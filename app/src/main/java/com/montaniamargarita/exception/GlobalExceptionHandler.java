package com.montaniamargarita.exception;

import com.montaniamargarita.dto.response.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Manejador global de excepciones.
 * Traduce cualquier excepción a un ErrorResponse uniforme.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /** Excepciones de dominio: usan su propio code y HttpStatus. */
    @ExceptionHandler(DomainException.class)
    public ResponseEntity<ErrorResponse> manejarDomain(DomainException ex, HttpServletRequest req) {
        ErrorResponse cuerpo = new ErrorResponse(
                Instant.now(),
                ex.getStatus().value(),
                ex.getCode(),
                ex.getMessage(),
                req.getRequestURI(),
                List.of());
        return ResponseEntity.status(ex.getStatus()).body(cuerpo);
    }

    /** Errores de validación de DTOs (Bean Validation). */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> manejarValidacion(MethodArgumentNotValidException ex,
                                                           HttpServletRequest req) {
        List<ErrorResponse.FieldError> errores = new ArrayList<>();
        ex.getBindingResult().getFieldErrors().forEach(fe ->
                errores.add(new ErrorResponse.FieldError(fe.getField(), fe.getDefaultMessage())));

        ErrorResponse cuerpo = new ErrorResponse(
                Instant.now(),
                HttpStatus.BAD_REQUEST.value(),
                "VALIDACION_FALLIDA",
                "La solicitud contiene errores de validación",
                req.getRequestURI(),
                errores);
        return ResponseEntity.badRequest().body(cuerpo);
    }

    /** Falta de permisos (rol insuficiente). */
    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorResponse> manejarAcceso(AccessDeniedException ex, HttpServletRequest req) {
        ErrorResponse cuerpo = new ErrorResponse(
                Instant.now(),
                HttpStatus.FORBIDDEN.value(),
                "ACCESO_DENEGADO",
                "No tiene permisos para realizar esta operación",
                req.getRequestURI(),
                List.of());
        return ResponseEntity.status(HttpStatus.FORBIDDEN).body(cuerpo);
    }

    /** Falta de autenticación. */
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<ErrorResponse> manejarAutenticacion(AuthenticationException ex,
                                                              HttpServletRequest req) {
        ErrorResponse cuerpo = new ErrorResponse(
                Instant.now(),
                HttpStatus.UNAUTHORIZED.value(),
                "TOKEN_INVALIDO",
                "Autenticación requerida",
                req.getRequestURI(),
                List.of());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(cuerpo);
    }

    /** Catch-all para errores no controlados. */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> manejarGenerico(Exception ex, HttpServletRequest req) {
        ErrorResponse cuerpo = new ErrorResponse(
                Instant.now(),
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "ERROR_INTERNO",
                "Ha ocurrido un error inesperado",
                req.getRequestURI(),
                List.of());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(cuerpo);
    }
}
