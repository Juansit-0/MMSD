package com.montaniamargarita.service.impl;

import com.montaniamargarita.dto.request.CrearPedidoRequest;
import com.montaniamargarita.dto.request.ModificarItemPedidoRequest;
import com.montaniamargarita.exception.MesaOcupadaException;
import com.montaniamargarita.exception.PedidoNoModificableException;
import com.montaniamargarita.exception.RecursoNoEncontradoException;
import com.montaniamargarita.model.Auditoria;
import com.montaniamargarita.model.ItemPedido;
import com.montaniamargarita.model.MesaEntity;
import com.montaniamargarita.model.PedidoEntity;
import com.montaniamargarita.model.ProductoEntity;
import com.montaniamargarita.model.enums.EstadoMesa;
import com.montaniamargarita.model.enums.EstadoPedido;
import com.montaniamargarita.repository.PedidoRepository;
import com.montaniamargarita.service.IMesaService;
import com.montaniamargarita.service.IPedidoService;
import com.montaniamargarita.service.IProductoService;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del servicio de gestión del pedido.
 * Coordina pedidos, mesas y productos.
 */
@Service
public class PedidoServiceImpl implements IPedidoService {

    private final PedidoRepository pedidoRepository;
    private final IMesaService mesaService;
    private final IProductoService productoService;

    public PedidoServiceImpl(PedidoRepository pedidoRepository,
                             IMesaService mesaService,
                             IProductoService productoService) {
        this.pedidoRepository = pedidoRepository;
        this.mesaService = mesaService;
        this.productoService = productoService;
    }

    @Override
    public PedidoEntity crearPedido(CrearPedidoRequest request, String meseroId) {
        // Validar que la mesa exista y esté libre
        MesaEntity mesa = mesaService.obtenerPorId(request.mesaId());
        if (mesa.getEstado() != EstadoMesa.LIBRE) {
            throw new MesaOcupadaException();
        }

        // Construir items con snapshot del producto
        List<ItemPedido> items = new ArrayList<>();
        for (CrearPedidoRequest.ItemRequest itReq : request.items()) {
            ProductoEntity producto = productoService.obtenerPorId(itReq.productoId());
            items.add(new ItemPedido(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    itReq.cantidad()));
        }

        // Crear pedido
        PedidoEntity pedido = new PedidoEntity(request.mesaId(), meseroId, items);
        pedido.setAuditoria(new Auditoria(meseroId, Instant.now()));
        PedidoEntity guardado = pedidoRepository.save(pedido);

        // Marcar mesa como ocupada
        mesaService.actualizarEstado(mesa.getId(), EstadoMesa.OCUPADA);
        return guardado;
    }

    @Override
    public PedidoEntity modificarItems(String pedidoId, ModificarItemPedidoRequest request) {
        PedidoEntity pedido = obtener(pedidoId);
        validarPedidoModificable(pedido);

        ProductoEntity producto = productoService.obtenerPorId(request.productoId());

        if (request.operacion() == ModificarItemPedidoRequest.Operacion.AGREGAR) {
            pedido.getItems().add(new ItemPedido(
                    producto.getId(),
                    producto.getNombre(),
                    producto.getPrecio(),
                    request.cantidad()));
        } else {
            // Eliminar primer item que coincida con el producto
            pedido.getItems().removeIf(item -> item.getProductoId().equals(producto.getId()));
        }

        pedido.recalcularTotal();
        pedido.setEstado(EstadoPedido.EN_CURSO);
        return pedidoRepository.save(pedido);
    }

    @Override
    public PedidoEntity cerrar(String pedidoId) {
        PedidoEntity pedido = obtener(pedidoId);
        validarPedidoModificable(pedido);
        pedido.setEstado(EstadoPedido.CERRADO);
        PedidoEntity guardado = pedidoRepository.save(pedido);
        mesaService.actualizarEstado(pedido.getMesaId(), EstadoMesa.CUENTA_SOLICITADA);
        return guardado;
    }

    @Override
    public PedidoEntity cancelar(String pedidoId, String razon, String adminId) {
        PedidoEntity pedido = obtener(pedidoId);
        if (pedido.getEstado() == EstadoPedido.FACTURADO
                || pedido.getEstado() == EstadoPedido.CANCELADO) {
            throw new PedidoNoModificableException(pedido.getEstado());
        }
        pedido.setEstado(EstadoPedido.CANCELADO);
        pedido.setRazonCancelacion(razon);
        if (pedido.getAuditoria() != null) {
            pedido.getAuditoria().setModificadoPor(adminId);
            pedido.getAuditoria().setFechaModificacion(Instant.now());
        }
        PedidoEntity guardado = pedidoRepository.save(pedido);
        mesaService.actualizarEstado(pedido.getMesaId(), EstadoMesa.LIBRE);
        return guardado;
    }

    @Override
    public PedidoEntity obtener(String pedidoId) {
        return pedidoRepository.findById(pedidoId)
                .orElseThrow(() -> new RecursoNoEncontradoException("Pedido", pedidoId));
    }

    @Override
    public List<PedidoEntity> listar() {
        return pedidoRepository.findAll();
    }

    @Override
    public void marcarFacturado(String pedidoId) {
        PedidoEntity pedido = obtener(pedidoId);
        pedido.setEstado(EstadoPedido.FACTURADO);
        pedidoRepository.save(pedido);
        mesaService.actualizarEstado(pedido.getMesaId(), EstadoMesa.LIBRE);
    }

    /** Verifica que el pedido esté en ABIERTO o EN_CURSO antes de modificarlo. */
    private void validarPedidoModificable(PedidoEntity pedido) {
        if (pedido.getEstado() != EstadoPedido.ABIERTO
                && pedido.getEstado() != EstadoPedido.EN_CURSO) {
            throw new PedidoNoModificableException(pedido.getEstado());
        }
    }
}
