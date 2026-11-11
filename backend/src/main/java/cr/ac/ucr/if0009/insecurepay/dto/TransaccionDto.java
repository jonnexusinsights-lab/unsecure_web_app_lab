package cr.ac.ucr.if0009.insecurepay.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class TransaccionDto {
    private Long id;
    private Long cuentaOrigenId;
    private String numeroCuentaOrigen;
    private String titularOrigen;
    private Long cuentaDestinoId;
    private String numeroCuentaDestino;
    private String titularDestino;
    private BigDecimal monto;
    private String conceptoHtml;
    private LocalDateTime fecha;
    private String estado;

    public TransaccionDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCuentaOrigenId() {
        return cuentaOrigenId;
    }

    public void setCuentaOrigenId(Long cuentaOrigenId) {
        this.cuentaOrigenId = cuentaOrigenId;
    }

    public String getNumeroCuentaOrigen() {
        return numeroCuentaOrigen;
    }

    public void setNumeroCuentaOrigen(String numeroCuentaOrigen) {
        this.numeroCuentaOrigen = numeroCuentaOrigen;
    }

    public String getTitularOrigen() {
        return titularOrigen;
    }

    public void setTitularOrigen(String titularOrigen) {
        this.titularOrigen = titularOrigen;
    }

    public Long getCuentaDestinoId() {
        return cuentaDestinoId;
    }

    public void setCuentaDestinoId(Long cuentaDestinoId) {
        this.cuentaDestinoId = cuentaDestinoId;
    }

    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public void setNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
    }

    public String getTitularDestino() {
        return titularDestino;
    }

    public void setTitularDestino(String titularDestino) {
        this.titularDestino = titularDestino;
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

