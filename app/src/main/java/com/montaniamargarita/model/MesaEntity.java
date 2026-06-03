package com.montaniamargarita.model;

import com.montaniamargarita.model.enums.EstadoMesa;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Mesa física del restaurante.
 * Mapea la colección "mesas" en MongoDB.
 */
@Document(collection = "mesas")
public class MesaEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private int numero;
    private EstadoMesa estado;

    // ====== Constructores ======

    public MesaEntity() {
    }

    public MesaEntity(int numero, EstadoMesa estado) {
        this.numero = numero;
        this.estado = estado;
    }

    // ====== Getters y setters ======

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public EstadoMesa getEstado() { return estado; }
    public void setEstado(EstadoMesa estado) { this.estado = estado; }

    // ====== toString ======

    @Override
    public String toString() {
        return "MesaEntity{" +
                "id='" + id + '\'' +
                ", numero=" + numero +
                ", estado=" + estado +
                '}';
    }
}
