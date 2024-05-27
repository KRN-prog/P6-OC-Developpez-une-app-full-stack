import { AfterViewInit, Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { take } from 'rxjs';
import { AuthService } from 'src/app/core/services/auth.service';
import { ThemesService } from 'src/app/core/services/themes.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss'],
})
export class ProfilComponent implements AfterViewInit {
  public save: string = 'Sauvegarder';
  user: any = null;
  themes!: any;
  subedThemes!: any;

  requestSent: boolean = false;
  formReturn!: string;

  updateUserData = {
    newUsername: '',
    newEmail: '',
  };

  UserRecreation = {
    id: '',
    username: '',
    email: '',
    password: '',
  };

  constructor(
    private router: Router,
    private route: ActivatedRoute,
    private authService: AuthService,
    private themeService: ThemesService
  ) {}

  ngAfterViewInit() {
    this.getAllThemes();
    this.getThemeSubscribed();
  }

  getThemeById(): void {
    this.authService
      .getUserById(this.route.snapshot.paramMap.get('id'))
      .subscribe((response) => {
        console.log(response);
        this.user = response;
      });
  }

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  setItem(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  removeItem(key: string): void {
    localStorage.removeItem(key);
  }

  updateUser(): void {
    this.authService
      .updateUser(this.getItem('user').id, this.updateUserData)
      .subscribe(
        (response) => {
          this.UserRecreation.id = this.getItem('user').id;
          this.UserRecreation.username = this.updateUserData.newUsername;
          this.UserRecreation.email = this.updateUserData.newEmail;
          this.UserRecreation.password = this.getItem('user').password;
          this.setItem('user', this.UserRecreation);

          this.requestSent = true;
          this.formReturn = 'Your profil was successfully updated';
        },
        (error) => {
          this.requestSent = true;
          this.formReturn = 'Something was wrong will updating your profil';
        }
      );
  }

  getAllThemes() {
    this.themeService
      .getAllThemes()
      .pipe(take(1))
      .subscribe((response) => {
        console.log(response);
        this.themes = response;
      });
  }

  getThemeSubscribed() {
    this.themeService
      .getAllSubedTheme(this.getItem('user').id)
      .subscribe((response) => {
        console.log(response);
        this.subedThemes = response;
      });
  }

  logOut() {
    this.removeItem('user');
    this.removeItem('userToken');
    this.router.navigate(['/']);
  }
}
