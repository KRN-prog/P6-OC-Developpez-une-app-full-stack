import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private registerUrl = 'http://localhost:8080/mdd/auth/register';
  private loginUrl = 'http://localhost:8080/mdd/auth/login';

  constructor(private http: HttpClient) {}

  registerUser(registerData: any): Observable<any> {
    return this.http.post<any>(this.registerUrl, registerData);
  }

  loginUser(loginData: any): Observable<any> {
    return this.http.get<any>(this.loginUrl, loginData);
  }
}
