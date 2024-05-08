import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ThemesService {
  private themes = 'http://localhost:8080/mdd/themes';
  private theme = 'http://localhost:8080/mdd/theme/';
  private postNewTheme = 'http://localhost:8080/mdd/theme';

  constructor(private http: HttpClient) {}

  getAllThemes(): Observable<any> {
    return this.http.get<any>(this.themes);
  }

  getThemeById(idTheme: any): Observable<any> {
    return this.http.get<any>(this.theme+idTheme, idTheme);
  }

  postTheme(themeRequest: any): Observable<any> {
    return this.http.post<any>(this.postNewTheme, themeRequest);
  }
}
