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
  subscribeToTheme: string = "S'abonner";
  unsubscribeToTheme: string = "Se désabonner";

  UserRecreation = {
    id: '',
    username: '',
    email: '',
    password: '',
    themes: '',
  }

  UpdateTheme = {
    id_article: '',
    id_user: '',
  }

  constructor(private authService: AuthService) {}

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  setItem(key: string, value: any): void {
    localStorage.setItem(key, JSON.stringify(value));
  }

  containsSimilarId(theme: any): boolean {
    let themeStringArr = this.getItem("user").themes
    let arrParse =  JSON.parse(themeStringArr);

    //return arrParse.some((item: { id: any; }) => themeId.includes(item.id));
    return arrParse.some((themeId: { id: any; }) => themeId === theme.id);
  }

  subscribeTheme(theme: any): void {
    let themeStringArr = this.getItem("user").themes
    let arrParse = JSON.parse(themeStringArr);
    arrParse.push(theme.id);
    let arrParsed = JSON.stringify(arrParse);

    this.UserRecreation.id = this.getItem("user").id;
    this.UserRecreation.username = this.getItem("user").username;
    this.UserRecreation.email = this.getItem("user").email;
    this.UserRecreation.password = this.getItem("user").password;
    this.UserRecreation.themes = arrParsed;
    this.setItem("user", this.UserRecreation)
    console.log(this.getItem("user"))

    this.UpdateTheme.id_article = theme.id;
    this.UpdateTheme.id_user = this.getItem("user").id;

    this.authService.updateUser(this.UpdateTheme).subscribe(
      (response) => {
        console.log(response);
      }
    );
  }

  unsubscribeTheme(theme: any): void {
    let themeStringArr = this.getItem("user").themes
    let arrParse = JSON.parse(themeStringArr);
    arrParse = arrParse.filter((item: any) => item !== theme.id);
    let arrParsed = JSON.stringify(arrParse);

    this.UserRecreation.id = this.getItem("user").id;
    this.UserRecreation.username = this.getItem("user").username;
    this.UserRecreation.email = this.getItem("user").email;
    this.UserRecreation.password = this.getItem("user").password;
    this.UserRecreation.themes = arrParsed;
    this.setItem("user", this.UserRecreation)
    console.log(this.getItem("user"))

    this.UpdateTheme.id_article = theme.id;
    this.UpdateTheme.id_user = this.getItem("user").id;

    this.authService.updateUser(this.UpdateTheme).subscribe(
      (response) => {
        console.log(response);
      }
    );
  }
}
