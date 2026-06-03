package com.montaniamargarita.repository;

import com.montaniamargarita.model.FacturaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.Instant;
import java.util.List;

/**
 * Repositorio Spring Data Mongo para FacturaEntity.
 */
public interface FacturaRepository extends MongoRepository<FacturaEntity, String> {

    /** Lista facturas en un rango de fechas (para reportes). */
    List<FacturaEntity> findByFechaEmisionBetween(Instant desde, Instant hasta);
}
