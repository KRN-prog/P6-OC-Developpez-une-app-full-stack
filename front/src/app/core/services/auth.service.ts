import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthService {
  private registerUrl = 'http://localhost:8080/mdd/auth/register';
  private loginUrl = 'http://localhost:8080/mdd/auth/login';
  private authMe = 'http://localhost:8080/mdd/auth/me';
  private user = 'http://localhost:8080/mdd/user/';
  private updateUserPath = 'http://localhost:8080/mdd/user';
  private updateDeleteUserPath = 'http://localhost:8080/mdd/user/delete';

  constructor(private http: HttpClient) {}

  headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  registerUser(registerData: any): Observable<any> {
    return this.http.post<any>(this.registerUrl, registerData);
  }

  loginUser(loginData: any): Observable<any> {
    return this.http.post<any>(this.loginUrl, loginData, {
      headers: this.headers,
    });
  }

  authUser(authHeaders: HttpHeaders): Observable<any> {
    return this.http.get<any>(this.authMe, { headers: authHeaders });
  }

  getUserById(idUser: any): Observable<any> {
    return this.http.get<any>(this.user+idUser);
  }

  updateUser(updateTheme: any): Observable<any> {
    return this.http.put<any>(this.updateUserPath, updateTheme);
  }

  updateDeleteUser(updateTheme: any): Observable<any> {
    return this.http.put<any>(this.updateDeleteUserPath, updateTheme);
  }
}
