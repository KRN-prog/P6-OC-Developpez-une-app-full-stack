import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticlesService } from 'src/app/core/services/articles.service';
import { MessageService } from 'src/app/core/services/message.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss']
})
export class ArticleComponent implements OnInit {
  article: any = null;
  message: any = [];

  messageRequest = {
    message: '',
    idArticle: '',
    idUser: '',
  };
  
  constructor(private route: ActivatedRoute, private articleService: ArticlesService, private messageService: MessageService) {}

  ngOnInit(): void {
    this.getThemeById();
    this.getAllMessage();
  }

  getThemeById(): void {
    this.articleService.getArticle(this.route.snapshot.paramMap.get('id')).subscribe(
      (response) => {
        console.log(response);
        this.article = response;
      }
    );
  }

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  getAllMessage(): void {
    this.messageService.getMessages(this.route.snapshot.paramMap.get('id')).subscribe(
      (response) => {
        console.log(response);
        this.message = response;
      }
    );
  }

  postMessage(): void {
    this.messageRequest.idUser = this.getItem("user").username;
    console.log(this.article.id)
    this.messageRequest.idArticle = this.article.id;

    this.messageService.postNewMessage(this.messageRequest).subscribe(
      (response) => {
        console.log(response);
        this.article = response;
      }
    );
  }
}
