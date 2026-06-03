package com.montaniamargarita.repository;

import com.montaniamargarita.model.ProductoEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Repositorio Spring Data Mongo para ProductoEntity.
 */
public interface ProductoRepository extends MongoRepository<ProductoEntity, String> {

    /** Lista los productos activos. */
    List<ProductoEntity> findByActivoTrue();
}
