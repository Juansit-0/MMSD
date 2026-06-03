package com.montaniamargarita.model;

import com.montaniamargarita.model.enums.EstadoPedido;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Pedido asociado a una mesa.
 * Contiene los items consumidos y su total acumulado.
 * Mapea la colección "pedidos" en MongoDB.
 */
@Document(collection = "pedidos")
public class PedidoEntity {

    @Id
    private String id;
    @Indexed
    private String mesaId;
    @Indexed
    private String meseroId;
    private EstadoPedido estado;
    private List<ItemPedido> items;
    private BigDecimal total;
    private String razonCancelacion;
    private Auditoria auditoria;

    // ====== Constructores ======

    public PedidoEntity() {
        this.items = new ArrayList<>();
        this.total = BigDecimal.ZERO;
    }

    public PedidoEntity(String mesaId, String meseroId, List<ItemPedido> items) {
        this.mesaId = mesaId;
        this.meseroId = meseroId;
        this.estado = EstadoPedido.ABIERTO;
        this.items = (items != null) ? items : new ArrayList<>();
        recalcularTotal();
    }

    // ====== Métodos de dominio ======

    /** Recalcula el total sumando los subtotales de todos los items. */
    public void recalcularTotal() {
        BigDecimal suma = BigDecimal.ZERO;
        if (items != null) {
            for (ItemPedido it : items) {
                suma = suma.add(it.getSubtotal());
            }
        }
        this.total = suma;
    }

    // ====== Getters y setters ======

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getMesaId() { return mesaId; }
    public void setMesaId(String mesaId) { this.mesaId = mesaId; }

    public String getMeseroId() { return meseroId; }
    public void setMeseroId(String meseroId) { this.meseroId = meseroId; }

    public EstadoPedido getEstado() { return estado; }
    public void setEstado(EstadoPedido estado) { this.estado = estado; }

    public List<ItemPedido> getItems() { return items; }
    public void setItems(List<ItemPedido> items) { this.items = items; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public String getRazonCancelacion() { return razonCancelacion; }
    public void setRazonCancelacion(String razonCancelacion) { this.razonCancelacion = razonCancelacion; }

    public Auditoria getAuditoria() { return auditoria; }
    public void setAuditoria(Auditoria auditoria) { this.auditoria = auditoria; }

    // ====== toString ======

    @Override
    public String toString() {
        return "PedidoEntity{" +
                "id='" + id + '\'' +
                ", mesaId='" + mesaId + '\'' +
                ", meseroId='" + meseroId + '\'' +
                ", estado=" + estado +
                ", items=" + items +
                ", total=" + total +
                ", razonCancelacion='" + razonCancelacion + '\'' +
                ", auditoria=" + auditoria +
                '}';
    }
}
