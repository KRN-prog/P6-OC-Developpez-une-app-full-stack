import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ThemesService {
  private themes = 'http://localhost:8080/mdd/themes';
  private theme = 'http://localhost:8080/mdd/theme/';
  private subOrDeleteToTheme = 'http://localhost:8080/mdd/theme/sub';
  private getSubToTheme = 'http://localhost:8080/mdd/theme/sub/';

  constructor(private http: HttpClient) {}

  getAllThemes(): Observable<any> {
    return this.http.get<any>(this.themes);
  }

  getThemeById(idTheme: any): Observable<any> {
    return this.http.get<any>(this.theme + idTheme, idTheme);
  }

  subscribeToTheme(themeSub: any): Observable<any> {
    return this.http.post<any>(this.subOrDeleteToTheme, themeSub);
  }

  deleteSubToTheme(deleteThemeSub: any): Observable<any> {
    const headers = new HttpHeaders({
      'Content-Type': 'application/json',
    });
    return this.http.delete<any>(this.subOrDeleteToTheme, {
      headers,
      body: deleteThemeSub,
    });
  }

  getAllSubedTheme(idThemeSub: any): Observable<any> {
    return this.http.get<any>(this.getSubToTheme + idThemeSub);
  }
}
