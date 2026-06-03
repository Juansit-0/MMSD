package com.montaniamargarita.dto.request;

import com.montaniamargarita.model.enums.MetodoPago;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

/**
 * Request para generar una factura.
 * Los datos del cliente son opcionales.
 */
public record CrearFacturaRequest(
        @NotBlank String pedidoId,
        @NotNull MetodoPago metodoPago,
        DatosClienteDTO datosCliente) {

    public record DatosClienteDTO(
            @Size(max = 100) String nombre,
            @Size(max = 20) String identificacion) {
    }
}
