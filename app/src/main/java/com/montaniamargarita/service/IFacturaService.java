package com.montaniamargarita.service;

import com.montaniamargarita.dto.request.CrearFacturaRequest;
import com.montaniamargarita.model.FacturaEntity;

import java.util.List;

/** Servicio de generación y consulta de facturas. */
public interface IFacturaService {

    FacturaEntity generar(CrearFacturaRequest request, String cajeroId);

    List<FacturaEntity> listar();
}
