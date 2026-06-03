package com.montaniamargarita.model;

/**
 * Datos opcionales del cliente que recibe la factura.
 * Embebido dentro de FacturaEntity.
 */
public class DatosCliente {

    private String nombre;
    private String identificacion;

    // ====== Constructores ======

    public DatosCliente() {
    }

    public DatosCliente(String nombre, String identificacion) {
        this.nombre = nombre;
        this.identificacion = identificacion;
    }

    // ====== Getters y setters ======

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getIdentificacion() { return identificacion; }
    public void setIdentificacion(String identificacion) { this.identificacion = identificacion; }

    // ====== toString ======

    @Override
    public String toString() {
        return "DatosCliente{" +
                "nombre='" + nombre + '\'' +
                ", identificacion='" + identificacion + '\'' +
                '}';
    }
}
