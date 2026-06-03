package com.montaniamargarita.controller;

import com.montaniamargarita.dto.response.ReporteVentasResponse;
import com.montaniamargarita.service.IReporteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

/**
 * Endpoints de reportes de ventas. Solo accesibles por ADMINISTRADOR.
 */
@RestController
@RequestMapping("/api/v1/reportes")
@PreAuthorize("hasRole('ADMINISTRADOR')")
@Tag(name="Reportes", description="Consolidado de ventas")
public class ReporteController {

    private final IReporteService reporteService;

    public ReporteController(IReporteService reporteService) {
        this.reporteService = reporteService;
    }

    /**
	 * 
	 * @param desde
	 * @param hasta
	 */
	@Operation(summary="Reporte de ventas en un rango de fechas (formato yyyy-MM-dd)")
	@GetMapping("/ventas")
    public ResponseEntity<ReporteVentasResponse> ventas(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate desde,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate hasta) {
        return ResponseEntity.ok(reporteService.ventas(desde, hasta));
    }
}
