import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import { AppComponent } from './app.component';
import { LandingPageComponent } from './pages/landing-page/landing-page.component';
import { InscriptionComponent } from './pages/inscription/inscription.component';
import { ConnexionComponent } from './pages/connexion/connexion.component';
import { ArticlesComponent } from './pages/articles/articles.component';
import { ThemesComponent } from './pages/themes/themes.component';
import { ProfilComponent } from './pages/profil/profil.component';
import { ArticleComponent } from './pages/article/article.component';
import { CreateArticleComponent } from './pages/create-article/create-article.component';
import { HeaderComponent } from './components/header/header.component';
import { ButtonComponent } from './components/button/button.component';
import { InputComponent } from './components/input/input.component';
import { ArticleSchemeComponent } from './components/article-scheme/article-scheme.component';
import { AppRoutingModule } from './app-routing.module';
import { ThemeSchemeComponent } from './components/theme-scheme/theme-scheme.component';
import { ThemeComponent } from './pages/theme/theme.component';
import { FormsModule } from '@angular/forms';
import { RegisterService } from './core/services/register.service';
import { HttpClientModule } from '@angular/common/http';

@NgModule({
  declarations: [
    AppComponent,
    LandingPageComponent,
    InscriptionComponent,
    ConnexionComponent,
    ArticlesComponent,
    ThemesComponent,
    ProfilComponent,
    ArticleComponent,
    CreateArticleComponent,
    HeaderComponent,
    ButtonComponent,
    InputComponent,
    ArticleSchemeComponent,
    ThemeSchemeComponent,
    ThemeComponent,
  ],
  imports: [BrowserModule, AppRoutingModule, FormsModule, HttpClientModule],
  providers: [RegisterService],
  bootstrap: [AppComponent],
})
export class AppModule {}
