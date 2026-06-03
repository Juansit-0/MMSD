package com.montaniamargarita.controller;

import com.montaniamargarita.dto.request.CrearFacturaRequest;
import com.montaniamargarita.dto.response.FacturaResponse;
import com.montaniamargarita.mapper.FacturaMapper;
import com.montaniamargarita.model.FacturaEntity;
import com.montaniamargarita.service.IFacturaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Endpoints de facturación. Solo accesibles por ADMINISTRADOR.
 */
@RestController
@RequestMapping("/api/v1/facturas")
@PreAuthorize("hasRole('ADMINISTRADOR')")
@Tag(name = "Facturas", description = "Generación y consulta de facturas")
public class FacturaController {

    private final IFacturaService facturaService;
    private final FacturaMapper facturaMapper;

    public FacturaController(IFacturaService facturaService, FacturaMapper facturaMapper) {
        this.facturaService = facturaService;
        this.facturaMapper = facturaMapper;
    }

    @Operation(summary = "Generar una factura a partir de un pedido cerrado")
    @PostMapping
    public ResponseEntity<FacturaResponse> generar(@Valid @RequestBody CrearFacturaRequest request,
                                                   Authentication authentication) {
        String cajeroId = (String) authentication.getPrincipal();
        FacturaEntity factura = facturaService.generar(request, cajeroId);
        return ResponseEntity.status(HttpStatus.CREATED).body(facturaMapper.toResponse(factura));
    }

    @Operation(summary = "Listar todas las facturas")
    @GetMapping
    public ResponseEntity<List<FacturaResponse>> listar() {
        List<FacturaResponse> respuesta = new ArrayList<>();
        for (FacturaEntity factura : facturaService.listar()) {
            respuesta.add(facturaMapper.toResponse(factura));
        }
        return ResponseEntity.ok(respuesta);
    }
}
