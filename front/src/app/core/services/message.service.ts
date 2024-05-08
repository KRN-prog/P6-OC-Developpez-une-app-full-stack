import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class MessageService {
  private newMessage = 'http://localhost:8080/mdd/post/message';
  private getAllMessages = 'http://localhost:8080/mdd/get/message/';

  constructor(private http: HttpClient) {}

  postNewMessage(messageRequest: any): Observable<any> {
    return this.http.post<any>(this.newMessage, messageRequest);
  }

  getMessages(idArticle: any): Observable<any> {
    return this.http.get<any>(this.getAllMessages+idArticle);
  }
}
