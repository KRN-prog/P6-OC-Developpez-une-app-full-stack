import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss'],
})
export class ConnexionComponent implements OnInit {
  public login: string = 'Se connecter';
  formFailure: boolean = false;

  loginData = {
    emailOrUsername: '',
    password: '',
  };

  constructor(private authService: AuthService, private router: Router) {}

  ngOnInit(): void {
    if (this.getItem('userToken') != null) {
      this.router.navigate(['/articles']);
    }
  }

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  goBack() {
    history.back();
  }

  loginUser(): void {
    this.authService.loginUser(this.loginData).subscribe(
      (response) => {
        localStorage.setItem('userToken', JSON.stringify(response.token));
        this.router.navigate(['/articles']);
      },
      (error) => {
        this.formFailure = true;
      }
    );
  }
}
