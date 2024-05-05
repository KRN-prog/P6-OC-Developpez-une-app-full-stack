import { Component } from '@angular/core';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.scss'],
})
export class ConnexionComponent {
  public login: string = 'Se connecter';

  loginData = {
    emailOrUsername: '',
    password: '',
  };

  loginUser(): void {
    
  }
}
