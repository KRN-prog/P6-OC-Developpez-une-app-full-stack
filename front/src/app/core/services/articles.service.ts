import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ArticleRequest } from '../models/request/ArticleRequest';

@Injectable({
  providedIn: 'root',
})
export class ArticlesService {
  private articles = 'http://localhost:8080/mdd/articles';
  private article = 'http://localhost:8080/mdd/article/';
  private postNewArticle = 'http://localhost:8080/mdd/article';

  constructor(private http: HttpClient) {}

  headers: HttpHeaders = new HttpHeaders({
    'Content-Type': 'application/json',
  });

  getAllArticles(): Observable<any> {
    return this.http.get<any>(this.articles);
  }

  getArticle(idArticle: any): Observable<any> {
    return this.http.get<any>(this.article+idArticle);
  }

  updateArticle(articleReqest: any, idArticle: any): Observable<any> {
    return this.http.get<any>(this.article, articleReqest)
  }

  postArticle(articleReqest: any): Observable<any> {
    return this.http.post<any>(this.postNewArticle, articleReqest)
  }
}
