package com.montaniamargarita.repository;

import com.montaniamargarita.model.PedidoEntity;
import com.montaniamargarita.model.enums.EstadoPedido;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * Repositorio Spring Data Mongo para PedidoEntity.
 */
public interface PedidoRepository extends MongoRepository<PedidoEntity, String> {

    /** Devuelve el pedido activo de una mesa (si existe). */
    Optional<PedidoEntity> findByMesaIdAndEstadoIn(String mesaId, Collection<EstadoPedido> estados);

    /** Lista pedidos de un mesero específico. */
    List<PedidoEntity> findByMeseroId(String meseroId);
}
