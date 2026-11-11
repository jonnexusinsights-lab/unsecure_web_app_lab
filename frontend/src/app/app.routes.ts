import { Routes } from '@angular/router';
import { LoginComponent } from './components/login/login.component';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { TransferirComponent } from './components/transferir/transferir.component';
import { TransaccionesComponent } from './components/transacciones/transacciones.component';

export const routes: Routes = [
  { path: '', redirectTo: 'dashboard', pathMatch: 'full' },
  { path: 'login', component: LoginComponent },
  { path: 'dashboard', component: DashboardComponent },
  { path: 'transferir', component: TransferirComponent },
  { path: 'transacciones', component: TransaccionesComponent },
  { path: '**', redirectTo: 'dashboard' }
];

