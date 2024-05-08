import { Component } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ThemesService } from 'src/app/core/services/themes.service';

@Component({
  selector: 'app-theme',
  templateUrl: './theme.component.html',
  styleUrls: ['./theme.component.scss']
})
export class ThemeComponent {
  theme: any = null;
  
  constructor(private route: ActivatedRoute, private themeService: ThemesService) {}

  ngOnInit(): void {
    this.getThemeById();
  }

  getThemeById(): void {
    this.themeService.getThemeById(this.route.snapshot.paramMap.get('id')).subscribe(
      (response) => {
        console.log(response);
        this.theme = response;
      }
    );
  }
}
