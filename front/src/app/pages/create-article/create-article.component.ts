import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ArticlesService } from 'src/app/core/services/articles.service';
import { ThemesService } from 'src/app/core/services/themes.service';

@Component({
  selector: 'app-create-article',
  templateUrl: './create-article.component.html',
  styleUrls: ['./create-article.component.scss']
})
export class CreateArticleComponent implements OnInit {
  public create: string = "Créer";
  themes: any = null;
  formFailure: any = false;
  formSuccess: any = false;

  themeData = {
    titre: '',
    date: '',
    auteur: '',
    content: '',
    theme: '',
  };

  constructor(private router: Router, private themesService: ThemesService, private articleService: ArticlesService) {}

  ngOnInit(): void {
    this.getAllThemes();
  }

  getAllThemes(): void {
    this.themesService.getAllThemes().subscribe(
      (response) => {
        console.log(response);
        this.themes = response;
      }
    );
  }

  getItem(key: string): any {
    const item = localStorage.getItem(key);
    return item ? JSON.parse(item) : null;
  }

  registerTheme(): void {
    var date = new Date();

    // Get year, month, and day part from the date
    var year = date.toLocaleString("default", { year: "numeric" });
    var month = date.toLocaleString("default", { month: "2-digit" });
    var day = date.toLocaleString("default", { day: "2-digit" });
    var formattedDate = year + "-" + month + "-" + day;

    this.themeData.auteur = this.getItem("user").username;
    this.themeData.date = formattedDate;
    this.articleService.postArticle(this.themeData).subscribe(
      (response) => {
        console.log("success")
        this.formSuccess = true;
      },
      (error) => {
        console.log("error")
        this.formFailure = true;
      }
    );
  }

}
