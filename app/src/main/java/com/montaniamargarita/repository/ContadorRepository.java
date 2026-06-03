package com.montaniamargarita.repository;

import com.montaniamargarita.model.ContadorEntity;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.stereotype.Repository;

/**
 * Repositorio personalizado para generar números consecutivos atómicos.
 * Usa MongoTemplate directamente porque Spring Data no expone $inc atómico.
 */
@Repository
public class ContadorRepository {

    private static final String ID_FACTURA = "factura_seq";

    private final MongoTemplate mongoTemplate;

    public ContadorRepository(MongoTemplate mongoTemplate) {
        this.mongoTemplate = mongoTemplate;
    }

    /**
     * Devuelve el siguiente número de factura.
     * Operación atómica: findAndModify con $inc.
     */
    public long siguienteFactura() {
        Query query = new Query(Criteria.where("_id").is(ID_FACTURA));
        Update update = new Update().inc("valor", 1L);
        FindAndModifyOptions opciones = FindAndModifyOptions.options().returnNew(true).upsert(true);
        ContadorEntity contador = mongoTemplate.findAndModify(query, update, opciones, ContadorEntity.class);
        return (contador != null) ? contador.getValor() : 1L;
    }
}
