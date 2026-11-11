import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, RouterModule } from '@angular/router';
import { BancoService } from '../../services/banco.service';
import { AuthService } from '../../services/auth.service';
import { Cuenta, TransferenciaRequest } from '../../models/banco.model';

@Component({
  selector: 'app-transferir',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './transferir.component.html'
})
export class TransferirComponent implements OnInit {
  todasLasCuentas: Cuenta[] = [];
  transferencia: TransferenciaRequest = {
    cuentaOrigenId: 2, // Por defecto cuenta de Maria Rojas (IDOR audit vector!)
    numeroCuentaDestino: 'CR05015202001026458901', // Cuenta de Carlos Mora
    monto: 50000,
    conceptoHtml: '<p>Transferencia por servicios prestados.</p>'
  };

  loading = false;
  errorMsg = '';
  successMsg = '';

  constructor(
    private bancoService: BancoService,
    public authService: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.bancoService.listarTodasLasCuentas().subscribe({
      next: (cuentas) => {
        this.todasLasCuentas = cuentas;
      }
    });
  }

  onTransferir(): void {
    if (!this.transferencia.cuentaOrigenId || !this.transferencia.numeroCuentaDestino || !this.transferencia.monto) {
      this.errorMsg = 'Por favor complete todos los datos de la transferencia.';
      return;
    }

    this.loading = true;
    this.errorMsg = '';
    this.successMsg = '';

    this.bancoService.transferir(this.transferencia).subscribe({
      next: (res) => {
        this.loading = false;
        this.successMsg = `¡Transferencia exitosa! Débito de ₡${res.monto} ejecutado en la cuenta origen #${res.cuentaOrigenId}.`;
        setTimeout(() => {
          this.router.navigate(['/transacciones']);
        }, 1500);
      },
      error: (err) => {
        this.loading = false;
        this.errorMsg = 'Error en la transacción: ' + (err.error?.message || err.message);
      }
    });
  }

  inyectarPayload(tipo: string): void {
    if (tipo === 'alert') {
      this.transferencia.conceptoHtml = `<img src="invalido" onerror="alert('Alerta Forense: Ejecución XSS en Comprobante InsecurePay!')">`;
    } else if (tipo === 'html') {
      this.transferencia.conceptoHtml = `<div style="background: #fee2e2; border: 2px solid red; padding: 10px; border-radius: 6px;">` +
        `<h4 style="color: #991b1b; margin:0;">ALERTA DE SEGURIDAD</h4>` +
        `<p style="margin: 4px 0 0; color: #7f1d1d;">Transferencia con concepto no verificado sanitariamente.</p></div>`;
    }
  }
}

