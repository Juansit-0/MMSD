package com.montaniamargarita.dto.response;

import com.montaniamargarita.model.enums.EstadoPedido;

import java.math.BigDecimal;
import java.util.List;

public record PedidoResponse(
        String id,
        String mesaId,
        String meseroId,
        EstadoPedido estado,
        List<ItemPedidoResponse> items,
        BigDecimal total,
        String razonCancelacion) {
}
