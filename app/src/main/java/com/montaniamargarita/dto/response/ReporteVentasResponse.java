package com.montaniamargarita.dto.response;

import com.montaniamargarita.model.enums.MetodoPago;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Map;

/**
 * Consolidado de ventas para un rango de fechas.
 */
public record ReporteVentasResponse(
        LocalDate desde,
        LocalDate hasta,
        BigDecimal totalVentas,
        long numeroFacturas,
        Map<MetodoPago, BigDecimal> desglosePorMetodoPago) {
}
