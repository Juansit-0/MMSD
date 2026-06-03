package com.montaniamargarita.service.impl;

import com.montaniamargarita.exception.RecursoNoEncontradoException;
import com.montaniamargarita.model.MesaEntity;
import com.montaniamargarita.model.enums.EstadoMesa;
import com.montaniamargarita.repository.MesaRepository;
import com.montaniamargarita.service.IMesaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de gestión de mesas.
 */
@Service
public class MesaServiceImpl implements IMesaService {

    private final MesaRepository mesaRepository;

    public MesaServiceImpl(MesaRepository mesaRepository) {
        this.mesaRepository = mesaRepository;
    }

    @Override
    public List<MesaEntity> listar() {
        return mesaRepository.findAll();
    }

    @Override
    public MesaEntity obtenerPorId(String id) {
        return mesaRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Mesa", id));
    }

    @Override
    public MesaEntity actualizarEstado(String mesaId, EstadoMesa nuevoEstado) {
        MesaEntity mesa = obtenerPorId(mesaId);
        mesa.setEstado(nuevoEstado);
        return mesaRepository.save(mesa);
    }
}
