import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ArticlesService {
  private articles = 'localhost:8080/mdd/auth/me';

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
}
