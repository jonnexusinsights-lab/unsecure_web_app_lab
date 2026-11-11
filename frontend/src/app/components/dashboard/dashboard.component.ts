import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AuthService } from '../../services/auth.service';
import { BancoService } from '../../services/banco.service';
import { Cuenta } from '../../models/banco.model';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [CommonModule, FormsModule, RouterModule],
  templateUrl: './dashboard.component.html'
})
export class DashboardComponent implements OnInit {
  misCuentas: Cuenta[] = [];
  cuentaEspiada: Cuenta | null = null;
  cuentaIdAInspeccionar: number = 2; // Por defecto cuenta de Maria Rojas
  loading = false;
  errorMsg = '';
  auditErrorMsg = '';

  constructor(
    public authService: AuthService,
    private bancoService: BancoService
  ) {}

  ngOnInit(): void {
    this.cargarMisCuentas();
  }

  cargarMisCuentas(): void {
    const user = this.authService.currentUser();
    if (!user) return;

    this.loading = true;
    this.bancoService.obtenerCuentasUsuario(user.usuarioId).subscribe({
      next: (cuentas) => {
        this.misCuentas = cuentas;
        this.loading = false;
      },
      error: (err) => {
        this.loading = false;
        this.errorMsg = 'Error al cargar cuentas: ' + (err.error?.message || err.message);
      }
    });
  }

  // [VULNERABILIDAD INTENCIONADA: IDOR Trigger en Consulta de Balances]
  inspeccionarCuentaPorId(): void {
    this.auditErrorMsg = '';
    this.cuentaEspiada = null;

    this.bancoService.obtenerCuentaPorId(this.cuentaIdAInspeccionar).subscribe({
      next: (cuenta) => {
        this.cuentaEspiada = cuenta;
      },
      error: (err) => {
        this.auditErrorMsg = 'Fallo al consultar cuenta: ' + (err.error?.message || err.message);
      }
    });
  }
}

