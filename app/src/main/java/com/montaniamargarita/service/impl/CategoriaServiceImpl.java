package com.montaniamargarita.service.impl;

import com.montaniamargarita.dto.request.CrearCategoriaRequest;
import com.montaniamargarita.model.CategoriaEntity;
import com.montaniamargarita.repository.CategoriaRepository;
import com.montaniamargarita.service.ICategoriaService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de gestión de categorías.
 */
@Service
public class CategoriaServiceImpl implements ICategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaServiceImpl(CategoriaRepository categoriaRepository) {
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<CategoriaEntity> listar() {
        return categoriaRepository.findAll();
    }

    @Override
    public CategoriaEntity crear(CrearCategoriaRequest request) {
        CategoriaEntity categoria = new CategoriaEntity(request.nombre(), request.descripcion());
        return categoriaRepository.save(categoria);
    }
}
