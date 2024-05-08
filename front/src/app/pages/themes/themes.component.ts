import { Component, OnInit } from '@angular/core';
import { ThemesService } from 'src/app/core/services/themes.service';

@Component({
  selector: 'app-themes',
  templateUrl: './themes.component.html',
  styleUrls: ['./themes.component.scss']
})
export class ThemesComponent implements OnInit {
  themes: any = null;
  
  constructor(private themeService: ThemesService) {}

  ngOnInit(): void {
    this.getAllThemes();
  }

  getAllThemes(): void {
    this.themeService.getAllThemes().subscribe(
      (response) => {
        console.log(response);
        this.themes = response;
      }
    );
  }
}
