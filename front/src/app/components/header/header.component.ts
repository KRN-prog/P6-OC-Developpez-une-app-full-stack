import { Component, EventEmitter, Output } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.scss'],
})
export class HeaderComponent {
  @Output() toggleSidenav = new EventEmitter<void>();

  constructor(private router: Router) {}

  gotToHome() {
    this.router.navigate(['/']);
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

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }
}
