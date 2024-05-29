import { HttpHeaders } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArticlesService } from 'src/app/core/services/articles.service';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss'],
})
export class ArticlesComponent implements OnInit {
  createArticleButton: string = 'Créer un article';
  articles: any = null;

  headers: HttpHeaders = new HttpHeaders({
    Authorization: `Bearer ${this.getItem('userToken')}`,
  });

  constructor(
    private router: Router,
    private articlesService: ArticlesService,
    private authService: AuthService
  ) {}

  ngOnInit(): void {
    if (this.getItem('userToken')) {
      this.authUser();
      this.getAllArticles();
    } else {
      this.router.navigate(['/']);
    }
  }

  goToCreateArticles(): void {
    this.router.navigate(['/create/article']);
  }

  goToArticle(idArticle: any): void {
    this.router.navigate(['/article/' + idArticle]);
  }

  setItem(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  removeItem(key: string): void {
    localStorage.removeItem(key);
  }

  getAllArticles(): void {
    this.articlesService.getAllArticles().subscribe((response) => {
      console.log(response);
      this.articles = response;
    });
  }

  authUser(): void {
    console.log(this.headers);
    this.authService.authUser(this.headers).subscribe(
      (response) => {
        if (this.getItem('user')) {
          this.removeItem('user');
        }
        localStorage.setItem('user', JSON.stringify(response));
        console.log(response);
      },
      (error) => {}
    );
  }

  goToProfil() {
    this.router.navigate(['/profil']);
  }

  goToThemes() {
    this.router.navigate(['/themes']);
  }

  goToArticles() {
    this.router.navigate(['/articles']);
  }

  isArticlesRoute(): boolean {
    return this.router.url.includes('/articles');
  }

  isThemesRoute(): boolean {
    return this.router.url.includes('/themes');
  }

  public mesDonnees: any = this.getItem('userToken'); //JSON.parse(localStorage.getItem('mesDonnees'));
}
