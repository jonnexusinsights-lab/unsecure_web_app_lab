package cr.ac.ucr.if0009.insecurepay.dto;

import java.math.BigDecimal;

public class TransferenciaDto {
    // [VULNERABILIDAD INTENCIONADA: A01:2021 - IDOR / BOLA]
    // Acepta cuentaOrigenId directamente del cliente sin verificar si pertenece al usuario autenticado
    private Long cuentaOrigenId;
    private String numeroCuentaDestino;
    private BigDecimal monto;
    private String conceptoHtml;

    public TransferenciaDto() {
    }

    public TransferenciaDto(Long cuentaOrigenId, String numeroCuentaDestino, BigDecimal monto, String conceptoHtml) {
        this.cuentaOrigenId = cuentaOrigenId;
        this.numeroCuentaDestino = numeroCuentaDestino;
        this.monto = monto;
        this.conceptoHtml = conceptoHtml;
    }

    public Long getCuentaOrigenId() {
        return cuentaOrigenId;
    }

    public void setCuentaOrigenId(Long cuentaOrigenId) {
        this.cuentaOrigenId = cuentaOrigenId;
    }

    public String getNumeroCuentaDestino() {
        return numeroCuentaDestino;
    }

    public void setNumeroCuentaDestino(String numeroCuentaDestino) {
        this.numeroCuentaDestino = numeroCuentaDestino;
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
}

