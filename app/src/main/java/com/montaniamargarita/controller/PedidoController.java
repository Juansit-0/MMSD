package com.montaniamargarita.controller;

import com.montaniamargarita.dto.request.CancelarPedidoRequest;
import com.montaniamargarita.dto.request.CrearPedidoRequest;
import com.montaniamargarita.dto.request.ModificarItemPedidoRequest;
import com.montaniamargarita.dto.response.PedidoResponse;
import com.montaniamargarita.mapper.PedidoMapper;
import com.montaniamargarita.model.PedidoEntity;
import com.montaniamargarita.service.IPedidoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Endpoints de gestión del ciclo de vida del pedido.
 * Mesero: crear, modificar, cerrar y listar.
 * Administrador: además, cancelar.
 */
@RestController
@RequestMapping("/api/v1/pedidos")
@Tag(name = "Pedidos", description = "Gestión de pedidos por mesa")
public class PedidoController {

    private final IPedidoService pedidoService;
    private final PedidoMapper pedidoMapper;

    public PedidoController(IPedidoService pedidoService, PedidoMapper pedidoMapper) {
        this.pedidoService = pedidoService;
        this.pedidoMapper = pedidoMapper;
    }

    @Operation(summary = "Crear un pedido en una mesa libre")
    @PostMapping
    @PreAuthorize("hasAnyRole('MESERO','ADMINISTRADOR')")
    public ResponseEntity<PedidoResponse> crear(@Valid @RequestBody CrearPedidoRequest request,
                                                Authentication authentication) {
        String meseroId = (String) authentication.getPrincipal();
        PedidoEntity creado = pedidoService.crearPedido(request, meseroId);
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoMapper.toResponse(creado));
    }

    @Operation(summary = "Agregar o eliminar items de un pedido activo")
    @PatchMapping("/{id}/items")
    @PreAuthorize("hasAnyRole('MESERO','ADMINISTRADOR')")
    public ResponseEntity<PedidoResponse> modificarItems(
            @PathVariable String id,
            @Valid @RequestBody ModificarItemPedidoRequest request) {
        PedidoEntity modificado = pedidoService.modificarItems(id, request);
        return ResponseEntity.ok(pedidoMapper.toResponse(modificado));
    }

    @Operation(summary = "Cerrar un pedido (cliente solicitó la cuenta)")
    @PostMapping("/{id}/cerrar")
    @PreAuthorize("hasAnyRole('MESERO','ADMINISTRADOR')")
    public ResponseEntity<PedidoResponse> cerrar(@PathVariable String id) {
        PedidoEntity cerrado = pedidoService.cerrar(id);
        return ResponseEntity.ok(pedidoMapper.toResponse(cerrado));
    }

    @Operation(summary = "Cancelar un pedido (solo administrador)")
    @PostMapping("/{id}/cancelar")
    @PreAuthorize("hasRole('ADMINISTRADOR')")
    public ResponseEntity<PedidoResponse> cancelar(
            @PathVariable String id,
            @Valid @RequestBody CancelarPedidoRequest request,
            Authentication authentication) {
        String adminId = (String) authentication.getPrincipal();
        PedidoEntity cancelado = pedidoService.cancelar(id, request.razon(), adminId);
        return ResponseEntity.ok(pedidoMapper.toResponse(cancelado));
    }

    @Operation(summary = "Listar pedidos")
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listar() {
        List<PedidoResponse> respuesta = new ArrayList<>();
        for (PedidoEntity pedido : pedidoService.listar()) {
            respuesta.add(pedidoMapper.toResponse(pedido));
        }
        return ResponseEntity.ok(respuesta);
    }
}
