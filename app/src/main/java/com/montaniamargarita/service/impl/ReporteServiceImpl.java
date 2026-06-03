package com.montaniamargarita.service.impl;

import com.montaniamargarita.dto.response.ReporteVentasResponse;
import com.montaniamargarita.model.FacturaEntity;
import com.montaniamargarita.model.enums.MetodoPago;
import com.montaniamargarita.repository.FacturaRepository;
import com.montaniamargarita.service.IReporteService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Implementación del servicio de generación de reportes de ventas.
 */
@Service
public class ReporteServiceImpl implements IReporteService {

    private static final ZoneId ZONA = ZoneId.of("America/Bogota");

    private final FacturaRepository facturaRepository;

    public ReporteServiceImpl(FacturaRepository facturaRepository) {
        this.facturaRepository = facturaRepository;
    }

    @Override
    public ReporteVentasResponse ventas(LocalDate desde, LocalDate hasta) {
        // Convertir fechas locales a Instant (inicio de día y fin de día)
        Instant inicio = desde.atStartOfDay(ZONA).toInstant();
        Instant fin = hasta.plusDays(1).atStartOfDay(ZONA).toInstant();

        List<FacturaEntity> facturas = facturaRepository.findByFechaEmisionBetween(inicio, fin);

        BigDecimal totalVentas = BigDecimal.ZERO;
        Map<MetodoPago, BigDecimal> desglose = new HashMap<>();
        for (MetodoPago metodo : MetodoPago.values()) {
            desglose.put(metodo, BigDecimal.ZERO);
        }

        for (FacturaEntity factura : facturas) {
            totalVentas = totalVentas.add(factura.getTotal());
            BigDecimal acumulado = desglose.get(factura.getMetodoPago());
            desglose.put(factura.getMetodoPago(), acumulado.add(factura.getTotal()));
        }

        return new ReporteVentasResponse(
                desde,
                hasta,
                totalVentas,
                facturas.size(),
                desglose);
    }
}
