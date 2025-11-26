package tn.esprit.spring.tpcafe_ons_kochtane.dto.Article;

import lombok.*;
import tn.esprit.spring.tpcafe_ons_kochtane.enumeration.TypeArticle;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleRequest {
    private String nomArticle;
    private float prixArticle;
    private TypeArticle typeArticle;
}
