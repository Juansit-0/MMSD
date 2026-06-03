package com.montaniamargarita.mapper;

import com.montaniamargarita.dto.response.FacturaResponse;
import com.montaniamargarita.dto.response.ItemPedidoResponse;
import com.montaniamargarita.model.DatosCliente;
import com.montaniamargarita.model.FacturaEntity;
import com.montaniamargarita.model.ItemPedido;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Convierte entidades de factura a DTOs de respuesta.
 */
@Component
public class FacturaMapper {

    public FacturaResponse toResponse(FacturaEntity factura) {
        List<ItemPedidoResponse> items = new ArrayList<>();
        if (factura.getItems() != null) {
            for (ItemPedido item : factura.getItems()) {
                items.add(new ItemPedidoResponse(
                        item.getProductoId(),
                        item.getNombreSnapshot(),
                        item.getPrecioSnapshot(),
                        item.getCantidad(),
                        item.getSubtotal()));
            }
        }

        DatosCliente datos = factura.getDatosCliente();
        String nombreCliente = (datos != null) ? datos.getNombre() : null;
        String identificacionCliente = (datos != null) ? datos.getIdentificacion() : null;

        return new FacturaResponse(
                factura.getId(),
                factura.getNumero(),
                factura.getPedidoId(),
                factura.getCajeroId(),
                items,
                factura.getTotal(),
                factura.getMetodoPago(),
                nombreCliente,
                identificacionCliente,
                factura.getFechaEmision());
    }
}
