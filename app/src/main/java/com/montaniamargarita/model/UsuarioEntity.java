package com.montaniamargarita.model;

import com.montaniamargarita.model.enums.Rol;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

/**
 * Usuario del sistema (mesero o administrador).
 * Mapea la colección "usuarios" en MongoDB.
 */
@Document(collection = "usuarios")
public class UsuarioEntity {

    @Id
    private String id;
    private String nombreCompleto;
    @Indexed(unique = true)
    private String usuario;
    private String pinHash;
    private Rol rol;
    private boolean activo;
    private Instant fechaCreacion;

    // ====== Constructores ======

    public UsuarioEntity() {
        // Constructor vacío requerido por Spring Data MongoDB.
    }

    public UsuarioEntity(String nombreCompleto, String usuario, String pinHash, Rol rol) {
        this.nombreCompleto = nombreCompleto;
        this.usuario = usuario;
        this.pinHash = pinHash;
        this.rol = rol;
        this.activo = true;
        this.fechaCreacion = Instant.now();
    }

    // ====== Getters y setters ======

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getUsuario() { return usuario; }
    public void setUsuario(String usuario) { this.usuario = usuario; }

    public String getPinHash() { return pinHash; }
    public void setPinHash(String pinHash) { this.pinHash = pinHash; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public boolean isActivo() { return activo; }
    public void setActivo(boolean activo) { this.activo = activo; }

    public Instant getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(Instant fechaCreacion) { this.fechaCreacion = fechaCreacion; }

    // ====== toString ======
    // No se incluye pinHash por seguridad.

    @Override
    public String toString() {
        return "UsuarioEntity{" +
                "id='" + id + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", usuario='" + usuario + '\'' +
                ", rol=" + rol +
                ", activo=" + activo +
                ", fechaCreacion=" + fechaCreacion +
                '}';
    }
}
