package com.montaniamargarita.dto.response;

import com.montaniamargarita.model.enums.MetodoPago;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

public record FacturaResponse(
        String id,
        long numero,
        String pedidoId,
        String cajeroId,
        List<ItemPedidoResponse> items,
        BigDecimal total,
        MetodoPago metodoPago,
        String nombreCliente,
        String identificacionCliente,
        Instant fechaEmision) {
}
