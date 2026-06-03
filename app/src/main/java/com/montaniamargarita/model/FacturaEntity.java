package com.montaniamargarita.model;

import com.montaniamargarita.model.enums.MetodoPago;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

/**
 * Factura emitida a partir de un pedido cerrado.
 * Contiene snapshot inmutable de los items consumidos.
 * Mapea la colección "facturas" en MongoDB.
 */
@Document(collection = "facturas")
public class FacturaEntity {

    @Id
    private String id;
    @Indexed(unique = true)
    private long numero;
    @Indexed(unique = true)
    private String pedidoId;
    private String cajeroId;
    private List<ItemPedido> items;
    private BigDecimal total;
    private MetodoPago metodoPago;
    private DatosCliente datosCliente;
    @Indexed
    private Instant fechaEmision;

    // ====== Constructores ======

    public FacturaEntity() {
        this.items = new ArrayList<>();
    }

    public FacturaEntity(long numero, String pedidoId, String cajeroId,
                         List<ItemPedido> items, BigDecimal total, MetodoPago metodoPago) {
        this.numero = numero;
        this.pedidoId = pedidoId;
        this.cajeroId = cajeroId;
        this.items = items;
        this.total = total;
        this.metodoPago = metodoPago;
        this.fechaEmision = Instant.now();
    }

    // ====== Getters y setters ======

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public long getNumero() { return numero; }
    public void setNumero(long numero) { this.numero = numero; }

    public String getPedidoId() { return pedidoId; }
    public void setPedidoId(String pedidoId) { this.pedidoId = pedidoId; }

    public String getCajeroId() { return cajeroId; }
    public void setCajeroId(String cajeroId) { this.cajeroId = cajeroId; }

    public List<ItemPedido> getItems() { return items; }
    public void setItems(List<ItemPedido> items) { this.items = items; }

    public BigDecimal getTotal() { return total; }
    public void setTotal(BigDecimal total) { this.total = total; }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    public DatosCliente getDatosCliente() { return datosCliente; }
    public void setDatosCliente(DatosCliente datosCliente) { this.datosCliente = datosCliente; }

    public Instant getFechaEmision() { return fechaEmision; }
    public void setFechaEmision(Instant fechaEmision) { this.fechaEmision = fechaEmision; }

    // ====== toString ======

    @Override
    public String toString() {
        return "FacturaEntity{" +
                "id='" + id + '\'' +
                ", numero=" + numero +
                ", pedidoId='" + pedidoId + '\'' +
                ", cajeroId='" + cajeroId + '\'' +
                ", total=" + total +
                ", metodoPago=" + metodoPago +
                ", datosCliente=" + datosCliente +
                ", fechaEmision=" + fechaEmision +
                '}';
    }
}
