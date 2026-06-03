package com.montaniamargarita.service;

import com.montaniamargarita.dto.request.CrearCategoriaRequest;
import com.montaniamargarita.model.CategoriaEntity;

import java.util.List;

/** Servicio de gestión del catálogo de categorías. */
public interface ICategoriaService {

    List<CategoriaEntity> listar();

    CategoriaEntity crear(CrearCategoriaRequest request);
}
