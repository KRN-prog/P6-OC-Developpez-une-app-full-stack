import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-landing-page',
  templateUrl: './landing-page.component.html',
  styleUrls: ['./landing-page.component.scss'],
})
export class LandingPageComponent implements OnInit {
  connexionButton: string = 'Se connecter';
  registerButton: string = "S'inscrire";

  constructor(private router: Router) {}

  ngOnInit(): void {
    if (this.getItem('userToken') != null) {
      this.router.navigate(['/articles']);
    }
  }

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  connexionRouting(): void {
    this.router.navigate(['/connexion']);
  }

  registerRouting(): void {
    this.router.navigate(['/register']);
  }
}
