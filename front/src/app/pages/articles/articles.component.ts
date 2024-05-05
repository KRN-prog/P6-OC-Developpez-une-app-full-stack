import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-articles',
  templateUrl: './articles.component.html',
  styleUrls: ['./articles.component.scss'],
})
export class ArticlesComponent implements OnInit {
  createArticleButton: string = 'Créer un article';

  constructor(private router: Router) {}

  ngOnInit(): void {
    if (!this.getItem('userToken')) {
      if (this.getItem('userToken')) {
      } else {
        this.router.navigate(['/']);
      }
    }
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

  public mesDonnees: any = this.getItem('userToken'); //JSON.parse(localStorage.getItem('mesDonnees'));
}
