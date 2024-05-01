import { Component } from '@angular/core';
import { RegisterService } from 'src/app/core/services/register.service';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.scss'],
})
export class InscriptionComponent {
  public signUp: string = 'S’inscrire';
  isSignUpPage: boolean = true;
  formDisabled: boolean = false;

  userData = {
    email: '',
    username: '',
    password: '',
  };

  constructor(private registerService: RegisterService) {}

  registerUser(): void {
    this.registerService.registerUser(this.userData).subscribe(
      (response) => {
        // Traitez la réponse de l'API ici si nécessaire
        console.log(response);
      },
      (error) => {
        // Gérez les erreurs ici
        console.error(error);
      }
    );
  }
}
