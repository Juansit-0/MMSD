package com.montaniamargarita.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Categoría del catálogo de productos (Bebidas, Platos Fuertes, Postres, etc.).
 * Mapea la colección "categorias" en MongoDB.
 */
@Document(collection = "categorias")
public class CategoriaEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private String nombre;
    private String descripcion;
    private boolean activo;

    // ====== Constructores ======

    public CategoriaEntity() {
    }

    public CategoriaEntity(String nombre, String descripcion) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.activo = true;
    }

    // ====== Getters y setters ======

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    // ====== toString ======

    @Override
    public String toString() {
        return "CategoriaEntity{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", activo=" + activo +
                '}';
    }
}
