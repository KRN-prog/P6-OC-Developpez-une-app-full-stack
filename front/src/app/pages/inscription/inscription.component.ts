import { Component } from '@angular/core';
import { Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss'],
})
export class InscriptionComponent {
  public signUp: string = 'S’inscrire';
  isSignUpPage: boolean = true;
  formDisabled: boolean = false;
  formFailure: boolean = false;

  registerData = {
    email: '',
    username: '',
    password: '',
  };

  constructor(private authService: AuthService, private router: Router) {}

  registerUser(): void {
    this.authService.registerUser(this.registerData).subscribe(
      (response) => {
        switch (response.response) {
          case 'User register successfully':
            this.router.navigate(['/connexion']);
            break;

          default:
            this.formFailure = true;
            break;
        }
      },
      (error) => {
        this.formFailure = true;
      }
    );
  }
}
