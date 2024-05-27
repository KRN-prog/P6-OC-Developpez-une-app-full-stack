import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ArticlesService } from 'src/app/core/services/articles.service';
import { MessageService } from 'src/app/core/services/message.service';

@Component({
  selector: 'app-article',
  templateUrl: './article.component.html',
  styleUrls: ['./article.component.scss'],
})
export class ArticleComponent implements OnInit {
  article: any = null;
  messages: any = [];

  messageRequest = {
    message: '',
    articleId: {
      articleId: 1,
      titre: '',
      date: '',
      auteur: '',
      content: '',
      theme: {
        id: 2,
        theme: '',
        title: '',
        contenu: '',
      },
    },
    userId: {
      userId: 1,
      username: '',
      email: '',
      password: '',
    },
  };

  constructor(
    private route: ActivatedRoute,
    private articleService: ArticlesService,
    private messageService: MessageService
  ) {}

  ngOnInit(): void {
    this.getThemeById();
    this.getAllMessage();
  }

  getThemeById(): void {
    this.articleService
      .getArticle(this.route.snapshot.paramMap.get('id'))
      .subscribe((response) => {
        console.log(response);
        this.article = response;
      });
  }

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  getAllMessage(): void {
    this.messageService
      .getMessages(this.route.snapshot.paramMap.get('id'))
      .subscribe((response) => {
        console.log(response);
        this.messages = response;
      });
  }

  postMessage(): void {
    this.messageRequest.articleId.articleId = this.article.id;
    this.messageRequest.articleId.titre = this.article.titre;
    this.messageRequest.articleId.date = this.article.date;
    this.messageRequest.articleId.auteur = this.article.auteur;
    this.messageRequest.articleId.content = this.article.content;
    this.messageRequest.articleId.theme = this.article.theme;

    this.messageRequest.userId.userId = this.getItem('user').id;
    this.messageRequest.userId.username = this.getItem('user').username;
    this.messageRequest.userId.email = this.getItem('user').email;
    this.messageRequest.userId.password = this.getItem('user').password;

    console.log(this.messageRequest);

    this.messageService
      .postNewMessage(this.messageRequest)
      .subscribe((response) => {
        window.location.reload();
      });
  }
}
