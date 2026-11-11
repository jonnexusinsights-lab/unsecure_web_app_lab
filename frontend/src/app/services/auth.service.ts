import { Injectable, signal } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, tap } from 'rxjs';
import { LoginResponse } from '../models/usuario.model';

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  private apiUrl = 'http://localhost:8080/api/auth';
  
  public currentUser = signal<LoginResponse | null>(null);

  constructor(private http: HttpClient) {
    const saved = localStorage.getItem('insecure_pay_user');
    if (saved) {
      try {
        this.currentUser.set(JSON.parse(saved));
      } catch (e) {
        localStorage.removeItem('insecure_pay_user');
      }
    }
  }

  login(username: string, password: string): Observable<LoginResponse> {
    return this.http.post<LoginResponse>(`${this.apiUrl}/login`, { username, password })
      .pipe(
        tap(user => {
          localStorage.setItem('insecure_pay_user', JSON.stringify(user));
          this.currentUser.set(user);
        })
      );
  }

  logout(): void {
    localStorage.removeItem('insecure_pay_user');
    this.currentUser.set(null);
  }

  isAuthenticated(): boolean {
    return this.currentUser() !== null;
  }
}

