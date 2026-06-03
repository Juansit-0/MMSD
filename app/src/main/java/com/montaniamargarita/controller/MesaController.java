package com.montaniamargarita.controller;

import com.montaniamargarita.dto.response.MesaResponse;
import com.montaniamargarita.model.MesaEntity;
import com.montaniamargarita.service.IMesaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Endpoint de consulta de mesas. Disponible para cualquier usuario autenticado.
 */
@RestController
@RequestMapping("/api/v1/mesas")
@Tag(name="Mesas", description="Consulta de mesas del restaurante")
public class MesaController {

    private final IMesaService mesaService;

    public MesaController(IMesaService mesaService) {
        this.mesaService = mesaService;
    }

    @Operation(summary = "Listar las 12 mesas con su estado actual")
    @GetMapping
    public ResponseEntity<List<MesaResponse>> listar() {
        List<MesaResponse> respuesta = new ArrayList<>();
        for (MesaEntity mesa : mesaService.listar()) {
            respuesta.add(new MesaResponse(mesa.getId(), mesa.getNumero(), mesa.getEstado()));
        }
        return ResponseEntity.ok(respuesta);
    }
}
