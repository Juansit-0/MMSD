package com.montaniamargarita.service.impl;

import com.montaniamargarita.dto.request.CrearProductoRequest;
import com.montaniamargarita.exception.RecursoNoEncontradoException;
import com.montaniamargarita.model.ProductoEntity;
import com.montaniamargarita.repository.CategoriaRepository;
import com.montaniamargarita.repository.ProductoRepository;
import com.montaniamargarita.service.IProductoService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementación del servicio de gestión de productos.
 */
@Service
public class ProductoServiceImpl implements IProductoService {

    private final ProductoRepository productoRepository;
    private final CategoriaRepository categoriaRepository;

    public ProductoServiceImpl(ProductoRepository productoRepository,
                               CategoriaRepository categoriaRepository) {
        this.productoRepository = productoRepository;
        this.categoriaRepository = categoriaRepository;
    }

    @Override
    public List<ProductoEntity> listar() {
        return productoRepository.findAll();
    }

    @Override
    public ProductoEntity obtenerPorId(String id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new RecursoNoEncontradoException("Producto", id));
    }

    @Override
    public ProductoEntity crear(CrearProductoRequest request) {
        // Verificar que la categoría exista
        categoriaRepository.findById(request.categoriaId())
                .orElseThrow(() -> new RecursoNoEncontradoException("Categoria", request.categoriaId()));

        ProductoEntity producto = new ProductoEntity(
                request.nombre(),
                request.categoriaId(),
                request.precio());
        return productoRepository.save(producto);
    }
}
