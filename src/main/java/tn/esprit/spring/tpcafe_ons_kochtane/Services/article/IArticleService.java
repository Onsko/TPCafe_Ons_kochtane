package tn.esprit.spring.tpcafe_ons_kochtane.Services.article;

import tn.esprit.spring.tpcafe_ons_kochtane.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Article;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Promotion;
import tn.esprit.spring.tpcafe_ons_kochtane.enumeration.TypeArticle;

import java.util.List;

public interface IArticleService {

    // 🔹 CRUD standard
    ArticleResponse addArticle(ArticleRequest article);
    List<ArticleResponse> saveArticles(List<ArticleRequest> articles);
    ArticleResponse selectArticleById(long id);
    List<ArticleResponse> selectAllArticles();
    void deleteArticleById(long id);
    void deleteAllArticles();
    long countingArticles();
    boolean verifyArticleById(long id);
    boolean deleteArticle(Long id);

    // 🔹 Méthodes JPQL spécifiques
    List<ArticleResponse> findByNom(String nom);
    List<ArticleResponse> findByType(TypeArticle type);
    List<ArticleResponse> findByPrix(float prix);
    boolean existsByNom(String nom);
    long countByType(TypeArticle type);
    List<ArticleResponse> findByNomContainsAndType(String nom, TypeArticle type);
    List<ArticleResponse> findByPrixBetweenAndTypes(float min, float max, List<TypeArticle> types);
    List<ArticleResponse> findByNomStartsWithOrderByPrix(String prefix);
    List<ArticleResponse> findArticlesWithMaxPriceByType(TypeArticle type);
    List<ArticleResponse> findByNomOrTypeOrderByPrixDesc(String nom, TypeArticle type);
    List<ArticleResponse> findByNomStartsWith(String prefix);
    List<ArticleResponse> findByNomEndsWith(String suffix);
    List<ArticleResponse> findWithoutType();
    List<ArticleResponse> findWithPrix();
    List<ArticleResponse> findArticlesWithActivePromotions();
    List<ArticleResponse> findByNomContainsAndPrixBetween(String nom, float min, float max);

    // 🔹 Méthode spécifique de l'étape 2
    Article ajouterArticleEtPromotions(Article article);

    // 🔹 Méthodes d’affectation/désaffectation des promotions
    void affecterPromotionAArticle(long idArticle, long idPromotion);
    void desaffecterPromotionAArticle(long idArticle, long idPromotion);



    void ajouterPromoEtAffecterAArticle(Promotion p, long idArticle);
}
