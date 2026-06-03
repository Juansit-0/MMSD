package com.montaniamargarita.service;

import com.montaniamargarita.dto.request.CrearPedidoRequest;
import com.montaniamargarita.dto.request.ModificarItemPedidoRequest;
import com.montaniamargarita.model.PedidoEntity;

import java.util.List;

/** Servicio de gestión del ciclo de vida del pedido. */
public interface IPedidoService {

    PedidoEntity crearPedido(CrearPedidoRequest request, String meseroId);

    PedidoEntity modificarItems(String pedidoId, ModificarItemPedidoRequest request);

    PedidoEntity cerrar(String pedidoId);

    PedidoEntity cancelar(String pedidoId, String razon, String adminId);

    PedidoEntity obtener(String pedidoId);

    List<PedidoEntity> listar();

    /** Marca un pedido como FACTURADO. Lo usa FacturaService tras emitir la factura. */
    void marcarFacturado(String pedidoId);
}
