package com.montaniamargarita.service.impl;

import com.montaniamargarita.dto.request.CrearFacturaRequest;
import com.montaniamargarita.exception.PedidoNoFacturableException;
import com.montaniamargarita.model.DatosCliente;
import com.montaniamargarita.model.FacturaEntity;
import com.montaniamargarita.model.PedidoEntity;
import com.montaniamargarita.model.enums.EstadoPedido;
import com.montaniamargarita.repository.ContadorRepository;
import com.montaniamargarita.repository.FacturaRepository;
import com.montaniamargarita.service.IFacturaService;
import com.montaniamargarita.service.IPedidoService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Implementación del servicio de generación de facturas.
 */
@Service
public class FacturaServiceImpl implements IFacturaService {

    private final FacturaRepository facturaRepository;
    private final ContadorRepository contadorRepository;
    private final IPedidoService pedidoService;

    public FacturaServiceImpl(FacturaRepository facturaRepository,
                              ContadorRepository contadorRepository,
                              IPedidoService pedidoService) {
        this.facturaRepository = facturaRepository;
        this.contadorRepository = contadorRepository;
        this.pedidoService = pedidoService;
    }

    @Override
    public FacturaEntity generar(CrearFacturaRequest request, String cajeroId) {
        // El pedido debe estar CERRADO
        PedidoEntity pedido = pedidoService.obtener(request.pedidoId());
        if (pedido.getEstado() != EstadoPedido.CERRADO) {
            throw new PedidoNoFacturableException(pedido.getEstado());
        }

        // Construir factura con snapshot inmutable de items
        long numero = contadorRepository.siguienteFactura();
        FacturaEntity factura = new FacturaEntity(
                numero,
                pedido.getId(),
                cajeroId,
                new ArrayList<>(pedido.getItems()),
                pedido.getTotal(),
                request.metodoPago());

        // Datos opcionales del cliente
        if (request.datosCliente() != null) {
            factura.setDatosCliente(new DatosCliente(
                    request.datosCliente().nombre(),
                    request.datosCliente().identificacion()));
        }

        FacturaEntity guardada = facturaRepository.save(factura);

        // Marcar pedido como facturado (libera la mesa)
        pedidoService.marcarFacturado(pedido.getId());
        return guardada;
    }

    @Override
    public List<FacturaEntity> listar() {
        return facturaRepository.findAll();
    }
}
