import { Component, Input } from '@angular/core';

@Component({
  selector: 'app-article-scheme',
  templateUrl: './article-scheme.component.html',
  styleUrls: ['./article-scheme.component.scss']
})
export class ArticleSchemeComponent {
  @Input() article!: any;
}
