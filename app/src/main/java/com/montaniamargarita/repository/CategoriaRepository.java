package com.montaniamargarita.repository;

import com.montaniamargarita.model.CategoriaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Repositorio Spring Data Mongo para CategoriaEntity.
 */
public interface CategoriaRepository extends MongoRepository<CategoriaEntity, String> {
}
