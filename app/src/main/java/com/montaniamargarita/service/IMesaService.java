package com.montaniamargarita.service;

import com.montaniamargarita.model.MesaEntity;
import com.montaniamargarita.model.enums.EstadoMesa;

import java.util.List;

/** Servicio de consulta y actualización de estados de mesa. */
public interface IMesaService {

    List<MesaEntity> listar();

    MesaEntity obtenerPorId(String id);

    /** Actualiza el estado de una mesa. Usado internamente por PedidoService. */
    MesaEntity actualizarEstado(String mesaId, EstadoMesa nuevoEstado);
}
