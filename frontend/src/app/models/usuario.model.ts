export interface Usuario {
  id: number;
  username: string;
  nombreCompleto: string;
  email: string;
  rol: 'ADMIN' | 'AUDITOR' | 'CLIENTE';
}

export interface LoginResponse {
  token: string;
  username: string;
  nombreCompleto: string;
  rol: string;
  usuarioId: number;
}

