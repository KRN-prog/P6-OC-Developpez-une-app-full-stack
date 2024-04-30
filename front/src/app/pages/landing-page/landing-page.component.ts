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

  connexionRouting(): void {
    this.router.navigate(['/connexion']);
  }

  registerRouting(): void {
    this.router.navigate(['/register']);
  }

  ngOnInit(): void {}
}
