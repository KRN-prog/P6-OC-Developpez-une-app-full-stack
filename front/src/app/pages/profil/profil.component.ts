import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { AuthService } from 'src/app/core/services/auth.service';
import { ThemesService } from 'src/app/core/services/themes.service';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.scss'],
})
export class ProfilComponent implements OnInit {
  public save: string = 'Sauvegarder';
  user: any = null;
  themeList: any = [];

  constructor(private router: Router, private route: ActivatedRoute, private authService: AuthService, private themesService: ThemesService) {}

  ngOnInit(): void {
    let themeStringArr = this.getItem("user").themes
    let arrParse = JSON.parse(themeStringArr);
    if (arrParse.length >= 1) {
      this.getArticleSub(); 
    }
  }

  getThemeById(): void {
    this.authService.getUserById(this.route.snapshot.paramMap.get('id')).subscribe(
      (response) => {
        console.log(response);
        this.user = response;
      }
    );
  }

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  removeItem(key: string): void {
    localStorage.removeItem(key);
  }

  getArticleSub(): void {
    let themeStringArr = this.getItem("user").themes
    let arrParse = JSON.parse(themeStringArr);
    for (let i = 0; i < arrParse.length; i++) {
      this.themesService.getThemeById(arrParse[i]).subscribe(
        (response) => {
          console.log(response);
          this.themeList.push(response);
        }
      );
    }
  }

  logOut(){
    this.removeItem("user");
    this.removeItem("userToken");
    this.router.navigate(['/']);
  }
}
