export interface Cuenta {
  id: number;
  usuarioId: number;
  titular: string;
  numeroCuenta: string;
  tipoCuenta: 'CORRIENTE' | 'AHORROS';
  saldo: number;
  moneda: string;
  activa: boolean;
}

export interface Transaccion {
  id: number;
  cuentaOrigenId: number;
  numeroCuentaOrigen: string;
  titularOrigen: string;
  cuentaDestinoId: number;
  numeroCuentaDestino: string;
  titularDestino: string;
  monto: number;
  conceptoHtml: string;
  fecha: string;
  estado: string;
}

export interface TransferenciaRequest {
  cuentaOrigenId: number;
  numeroCuentaDestino: string;
  monto: number;
  conceptoHtml: string;
}

