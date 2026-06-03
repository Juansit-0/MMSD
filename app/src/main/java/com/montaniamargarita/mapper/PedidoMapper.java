package com.montaniamargarita.mapper;

import com.montaniamargarita.dto.response.ItemPedidoResponse;
import com.montaniamargarita.dto.response.PedidoResponse;
import com.montaniamargarita.model.ItemPedido;
import com.montaniamargarita.model.PedidoEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Convierte entidades de pedido a DTOs de respuesta.
 */
@Component
public class PedidoMapper {

    public PedidoResponse toResponse(PedidoEntity pedido) {
        List<ItemPedidoResponse> items = new ArrayList<>();
        if (pedido.getItems() != null) {
            for (ItemPedido item : pedido.getItems()) {
                items.add(new ItemPedidoResponse(
                        item.getProductoId(),
                        item.getNombreSnapshot(),
                        item.getPrecioSnapshot(),
                        item.getCantidad(),
                        item.getSubtotal()));
            }
        }
        return new PedidoResponse(
                pedido.getId(),
                pedido.getMesaId(),
                pedido.getMeseroId(),
                pedido.getEstado(),
                items,
                pedido.getTotal(),
                pedido.getRazonCancelacion());
    }
}
