package com.montaniamargarita.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Documento contador para generar números consecutivos atómicos.
 * Se usa para el número de factura (id = "factura_seq").
 */
@Document(collection = "contadores")
public class ContadorEntity {

    @Id
    private String id;
    private long valor;

    // ====== Constructores ======

    public ContadorEntity() {
    }

    public ContadorEntity(String id, long valor) {
        this.id = id;
        this.valor = valor;
    }

    // ====== Getters y setters ======

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public long getValor() { return valor; }
    public void setValor(long valor) { this.valor = valor; }

    // ====== toString ======

    @Override
    public String toString() {
        return "ContadorEntity{id='" + id + "', valor=" + valor + "}";
    }
}
