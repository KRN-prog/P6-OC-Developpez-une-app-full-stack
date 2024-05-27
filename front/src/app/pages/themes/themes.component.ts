import { Component, OnInit } from '@angular/core';
import { take } from 'rxjs';
import { ThemesService } from 'src/app/core/services/themes.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss'],
})
export class ThemesComponent implements OnInit {
  themes: any = null;
  subedThemes: any = null;

  constructor(private themeService: ThemesService) {}

  ngOnInit(): void {
    this.getAllThemes();
    this.getThemeSubscribed();
  }

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  getThemeSubscribed() {
    this.themeService
      .getAllSubedTheme(this.getItem('user').id)
      .subscribe((response) => {
        console.log(response);
        this.subedThemes = response;
      });
  }

  getAllThemes() {
    this.themeService
      .getAllThemes()
      .pipe(take(1))
      .subscribe((response) => {
        this.themes = response;
      });
  }
}
