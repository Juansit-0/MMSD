package com.montaniamargarita.model;

import java.time.Instant;

/**
 * Metadatos de auditoría embebidos en agregados (PedidoEntity, etc.).
 * Registra quién creó/modificó la entidad y cuándo.
 */
public class Auditoria {

    private String creadoPor;
    private Instant fechaCreacion;
    private String modificadoPor;
    private Instant fechaModificacion;

    // ====== Constructores ======

    public Auditoria() {
    }

    public Auditoria(String creadoPor, Instant fechaCreacion) {
        this.creadoPor = creadoPor;
        this.fechaCreacion = fechaCreacion;
    }

    // ====== Getters y setters ======

    public String getCreadoPor() { return creadoPor; }
    public void setCreadoPor(String creadoPor) { this.creadoPor = creadoPor; }

    public Instant getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Instant fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    public String getModificadoPor() { return modificadoPor; }
    public void setModificadoPor(String modificadoPor) { this.modificadoPor = modificadoPor; }

    public Instant getFechaModificacion() { return fechaModificacion; }
    public void setFechaModificacion(Instant fechaModificacion) { this.fechaModificacion = fechaModificacion; }

    // ====== toString ======

    @Override
    public String toString() {
        return "Auditoria{" +
                "creadoPor='" + creadoPor + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", modificadoPor='" + modificadoPor + '\'' +
                ", fechaModificacion=" + fechaModificacion +
                '}';
    }
}
