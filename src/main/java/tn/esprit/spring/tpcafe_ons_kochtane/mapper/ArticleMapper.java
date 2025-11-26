package tn.esprit.spring.tpcafe_ons_kochtane.mapper;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Article;

@Mapper(componentModel = "spring")
public interface ArticleMapper {
    // Convertit une entité Article en DTO ArticleResponse
    ArticleResponse todto(Article article);

    // Convertit un DTO ArticleRequest en entité Article
    Article toentity(ArticleRequest articleRequest);
}
