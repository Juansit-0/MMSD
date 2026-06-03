package com.montaniamargarita.model;

import java.math.BigDecimal;

/**
 * Línea de un pedido. Es un objeto de valor embebido dentro de PedidoEntity
 * y dentro de FacturaEntity (como snapshot inmutable).
 *
 * Guarda copia del nombre y precio del producto al momento de crearse,
 * para que la factura sobreviva a cambios futuros en el catálogo.
 */
public class ItemPedido {

    private String productoId;
    private String nombreSnapshot;
    private BigDecimal precioSnapshot;
    private int cantidad;
    private BigDecimal subtotal;

    // ====== Constructores ======

    public ItemPedido() {
    }

    public ItemPedido(String productoId, String nombreSnapshot, BigDecimal precioSnapshot, int cantidad) {
        this.productoId = productoId;
        this.nombreSnapshot = nombreSnapshot;
        this.precioSnapshot = precioSnapshot;
        this.cantidad = cantidad;
        this.subtotal = precioSnapshot.multiply(BigDecimal.valueOf(cantidad));
    }

    // ====== Métodos de dominio ======

    /** Recalcula el subtotal en base al precio snapshot y la cantidad. */
    public BigDecimal calcularSubtotal() {
        this.subtotal = precioSnapshot.multiply(BigDecimal.valueOf(cantidad));
        return this.subtotal;
    }

    // ====== Getters y setters ======

    public String getProductoId() { return productoId; }
    public void setProductoId(String productoId) { this.productoId = productoId; }

    public String getNombreSnapshot() { return nombreSnapshot; }
    public void setNombreSnapshot(String nombreSnapshot) { this.nombreSnapshot = nombreSnapshot; }

    public BigDecimal getPrecioSnapshot() { return precioSnapshot; }
    public void setPrecioSnapshot(BigDecimal precioSnapshot) { this.precioSnapshot = precioSnapshot; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public BigDecimal getSubtotal() { return subtotal; }
    public void setSubtotal(BigDecimal subtotal) { this.subtotal = subtotal; }

    // ====== toString ======

    @Override
    public String toString() {
        return "ItemPedido{" +
                "productoId='" + productoId + '\'' +
                ", nombreSnapshot='" + nombreSnapshot + '\'' +
                ", precioSnapshot=" + precioSnapshot +
                ", cantidad=" + cantidad +
                ", subtotal=" + subtotal +
                '}';
    }
}
