package cr.ac.ucr.if0009.insecurepay.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "TRANSACCIONES")
public class Transaccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_origen_id", nullable = false)
    private CuentaBancaria cuentaOrigen;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cuenta_destino_id", nullable = false)
    private CuentaBancaria cuentaDestino;

    @Column(nullable = false, precision = 15, scale = 2)
    private BigDecimal monto;

    // [VULNERABILIDAD INTENCIONADA: A03:2021 - Injection (Stored XSS)]
    // Almacena cadenas HTML y scripts arbitrarios en la base de datos sin sanitizar
    @Lob
    @Column(name = "concepto_html")
    private String conceptoHtml;

    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(nullable = false, length = 20)
    private String estado;

    @PrePersist
    public void prePersist() {
        if (fecha == null) {
            fecha = LocalDateTime.now();
        }
        if (estado == null) {
            estado = "COMPLETADA";
        }
    }

    public Transaccion() {
    }

    public Transaccion(CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino, 
                       BigDecimal monto, String conceptoHtml) {
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.monto = monto;
        this.conceptoHtml = conceptoHtml;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CuentaBancaria getCuentaOrigen() {
        return cuentaOrigen;
    }

    public void setCuentaOrigen(CuentaBancaria cuentaOrigen) {
        this.cuentaOrigen = cuentaOrigen;
    }

    public CuentaBancaria getCuentaDestino() {
        return cuentaDestino;
    }

    public void setCuentaDestino(CuentaBancaria cuentaDestino) {
        this.cuentaDestino = cuentaDestino;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public String getConceptoHtml() {
        return conceptoHtml;
    }

    public void setConceptoHtml(String conceptoHtml) {
        this.conceptoHtml = conceptoHtml;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}

