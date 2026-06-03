package com.montaniamargarita.repository;

import com.montaniamargarita.model.MesaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

/**
 * Repositorio Spring Data Mongo para MesaEntity.
 */
public interface MesaRepository extends MongoRepository<MesaEntity, String> {

    /** Busca una mesa por su número visible (1 a 12). */
    Optional<MesaEntity> findByNumero(int numero);
}
