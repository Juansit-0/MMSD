package com.montaniamargarita.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * Producto del catálogo del restaurante.
 * Pertenece a una categoría (referenciada por id).
 * Mapea la colección "productos" en MongoDB.
 */
@Document(collection = "productos")
public class ProductoEntity {

    @Id
    private String id;
    private String nombre;
    private String categoriaId;
    private BigDecimal precio;   // Precio con impuesto incluido
    private boolean activo;
    private Instant fechaCreacion;

    // ====== Constructores ======

    public ProductoEntity() {
    }

    public ProductoEntity(String nombre, String categoriaId, BigDecimal precio) {
        this.nombre = nombre;
        this.categoriaId = categoriaId;
        this.precio = precio;
        this.activo = true;
        this.fechaCreacion = Instant.now();
    }

    // ====== Getters y setters ======

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getCategoriaId() { return categoriaId; }
    public void setCategoriaId(String categoriaId) { this.categoriaId = categoriaId; }

    public BigDecimal getPrecio() { return precio; }
    public void setPrecio(BigDecimal precio) { this.precio = precio; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Instant getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Instant fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    // ====== toString ======

    @Override
    public String toString() {
        return "ProductoEntity{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", categoriaId='" + categoriaId + '\'' +
                ", precio=" + precio +
                ", activo=" + activo +
                '}';
    }
}
