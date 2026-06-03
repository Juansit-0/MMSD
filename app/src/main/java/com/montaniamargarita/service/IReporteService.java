package com.montaniamargarita.service;

import com.montaniamargarita.dto.response.ReporteVentasResponse;

import java.time.LocalDate;

/** Servicio de generación de reportes de ventas. */
public interface IReporteService {

    ReporteVentasResponse ventas(LocalDate desde, LocalDate hasta);
}
