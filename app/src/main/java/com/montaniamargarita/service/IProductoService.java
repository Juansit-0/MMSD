package com.montaniamargarita.service;

import com.montaniamargarita.dto.request.CrearProductoRequest;
import com.montaniamargarita.model.ProductoEntity;

import java.util.List;

/** Servicio de gestión del catálogo de productos. */
public interface IProductoService {

    List<ProductoEntity> listar();

    ProductoEntity obtenerPorId(String id);

    ProductoEntity crear(CrearProductoRequest request);
}
