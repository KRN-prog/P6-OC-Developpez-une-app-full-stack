import { Component, Input, OnInit } from '@angular/core';
import { AuthService } from 'src/app/core/services/auth.service';
import { ThemesService } from 'src/app/core/services/themes.service';

@Component({
  selector: 'app-theme-scheme',
  templateUrl: './theme-scheme.component.html',
  styleUrls: ['./theme-scheme.component.scss'],
})
export class ThemeSchemeComponent {
  @Input() forTheme!: any;
  @Input() subedTheme!: any;
  subscribeToTheme: string = "S'abonner";
  unsubscribeToTheme: string = 'Se désabonner';

  UserRecreation = {
    id: '',
    username: '',
    email: '',
    password: '',
    themes: '',
  };

  sub = {
    user: {
      userId: 1,
      username: '',
      email: '',
      password: '',
    },
    theme: {
      id: 1,
      theme: '',
      title: '',
      contenu: '',
    },
  };

  unsub = {
    themeId: 1,
    userId: 1,
  };

  constructor(private themeService: ThemesService) {}

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  setItem(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  containsSimilarId(forTheme: any): any {
    if (this.subedTheme) {
      const matchingTheme = this.subedTheme.find(
        (theme: { theme: { id: any }; user: { userId: any } }) =>
          theme.theme?.id === forTheme.id &&
          theme.user?.userId === this.getItem('user').id
      );
      if (matchingTheme) {
        return true;
      }
    }
  }

  subscribeTheme(forTheme: any): void {
    this.sub.user.userId = this.getItem('user').id;
    this.sub.user.email = this.getItem('user').email;
    this.sub.user.username = this.getItem('user').username;
    this.sub.user.password = this.getItem('user').password;
    this.sub.theme = forTheme;

    console.log(this.sub);
    this.themeService.subscribeToTheme(this.sub).subscribe((response) => {
      window.location.reload();
    });
  }

  unsubscribeTheme(forTheme: any): void {
    this.unsub.themeId = forTheme.id;
    this.unsub.userId = this.getItem('user').id;

    console.log(this.unsub);
    this.themeService.deleteSubToTheme(this.unsub).subscribe((response) => {
      window.location.reload();
    });
  }
}
