package tn.esprit.spring.tpcafe_ons_kochtane.Services.article;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Article;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Promotion;
import tn.esprit.spring.tpcafe_ons_kochtane.enumeration.TypeArticle;
import tn.esprit.spring.tpcafe_ons_kochtane.mapper.ArticleMapper;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.ArticleRepository;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.PromotionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService implements IArticleService {

    private final ArticleRepository articleRepository;
    private final PromotionRepository promotionRepository;
    private final ArticleMapper articleMapper;

    // ------------------- CRUD standard -------------------
    @Override
    public ArticleResponse addArticle(ArticleRequest articleRequest) {
        // enregistrer un article
        Article saved = articleRepository.save(articleMapper.toentity(articleRequest));
        return articleMapper.todto(saved);
    }

    @Override
    public List<ArticleResponse> saveArticles(List<ArticleRequest> articles) {
        // enregistrer une liste d'articles
        List<Article> saved = articleRepository.saveAll(
                articles.stream().map(articleMapper::toentity).toList()
        );
        return saved.stream().map(articleMapper::todto).toList();
    }

    @Override
    public ArticleResponse selectArticleById(long id) {
        // rechercher un article par ID
        return articleMapper.todto(
                articleRepository.findById(id).orElseThrow(() -> new RuntimeException("Article non trouvé"))
        );
    }

    @Override
    public List<ArticleResponse> selectAllArticles() {
        // récupérer tous les articles
        return articleRepository.findAll().stream().map(articleMapper::todto).toList();
    }

    @Override
    public void deleteArticleById(long id) {
        // supprimer un article par ID
        articleRepository.deleteById(id);
    }

    @Override
    public void deleteAllArticles() {
        // supprimer tous les articles
        articleRepository.deleteAll();
    }

    @Override
    public long countingArticles() {
        // compter tous les articles
        return articleRepository.count();
    }

    @Override
    public boolean verifyArticleById(long id) {
        // vérifier l'existence d'un article par ID
        return articleRepository.existsById(id);
    }

    @Override
    public boolean deleteArticle(Long id) {
        // supprimer un article si existant
        if (articleRepository.existsById(id)) {
            articleRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ------------------- JPQL / Requêtes spécifiques -------------------
    @Override
    public List<ArticleResponse> findByNom(String nom) {
        // rechercher articles par nom exact
        return articleRepository.findByNom(nom).stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findByType(TypeArticle type) {
        // rechercher articles par type
        return articleRepository.findByType(type).stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findByPrix(float prix) {
        // rechercher articles par prix exact
        return articleRepository.findByPrix(prix).stream().map(articleMapper::todto).toList();
    }

    @Override
    public boolean existsByNom(String nom) {
        // vérifier existence d’un article par nom
        return articleRepository.existsByNom(nom);
    }

    @Override
    public long countByType(TypeArticle type) {
        // compter les articles par type
        return articleRepository.countByType(type);
    }

    @Override
    public List<ArticleResponse> findByNomContainsAndType(String nom, TypeArticle type) {
        // rechercher articles par nom partiel et type
        return articleRepository.findByNomContainsAndType(nom, type).stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findByPrixBetweenAndTypes(float min, float max, List<TypeArticle> types) {
        // rechercher articles par prix dans une plage et types sélectionnés
        return articleRepository.findByPrixBetweenAndTypes(min, max, types).stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findByNomStartsWithOrderByPrix(String prefix) {
        // nom commence par + tri par prix
        return articleRepository.findByNomStartsWithOrderByPrix(prefix).stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findArticlesWithMaxPriceByType(TypeArticle type) {
        // articles par type avec prix max
        return articleRepository.findArticlesWithMaxPriceByType(type).stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findByNomOrTypeOrderByPrixDesc(String nom, TypeArticle type) {
        // nom ou type + tri prix DESC
        return articleRepository.findByNomOrTypeOrderByPrixDesc(nom, type).stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findByNomStartsWith(String prefix) {
        // nom commence par
        return articleRepository.findByNomStartsWith(prefix).stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findByNomEndsWith(String suffix) {
        // nom se termine par
        return articleRepository.findByNomEndsWith(suffix).stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findWithoutType() {
        // articles sans type
        return articleRepository.findWithoutType().stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findWithPrix() {
        // articles avec prix renseigné
        return articleRepository.findWithPrix().stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findArticlesWithActivePromotions() {
        // articles avec promotions actives
        return articleRepository.findArticlesWithActivePromotions().stream().map(articleMapper::todto).toList();
    }

    @Override
    public List<ArticleResponse> findByNomContainsAndPrixBetween(String nom, float min, float max) {
        // nom contient + prix dans plage
        return articleRepository.findByNomContainsAndPrixBetween(nom, min, max).stream().map(articleMapper::todto).toList();
    }

    // ------------------- Méthode cascade ajouterArticleEtPromotions -------------------
    @Override
    public Article ajouterArticleEtPromotions(Article article) {
        // cascade => article + promotions
        return articleRepository.save(article);
    }

    // ------------------- Méthodes d’affectation / désaffectation -------------------
    @Override
    public void affecterPromotionAArticle(long idArticle, long idPromotion) {
        // find by id => article
        Article article = articleRepository.findById(idArticle)
                .orElseThrow(() -> new RuntimeException("Article introuvable"));

        // find by id => promotion
        Promotion promotion = promotionRepository.findById(idPromotion)
                .orElseThrow(() -> new RuntimeException("Promotion introuvable"));

        // ajouter la promotion à la liste des promotions de l’article
        article.getPromotions().add(promotion);

        // sauvegarder l’article mis à jour
        articleRepository.save(article);
    }

    @Override
    public void desaffecterPromotionAArticle(long idArticle, long idPromotion) {
        // find by id => article
        Article article = articleRepository.findById(idArticle)
                .orElseThrow(() -> new RuntimeException("Article introuvable"));

        // find by id => promotion
        Promotion promotion = promotionRepository.findById(idPromotion)
                .orElseThrow(() -> new RuntimeException("Promotion introuvable"));

        // retirer la promotion de la liste des promotions
        article.getPromotions().remove(promotion);

        // sauvegarder l’article mis à jour
        articleRepository.save(article);
    }

    @Override
    public void ajouterPromoEtAffecterAArticle(Promotion p, long idArticle) {

            Article article = articleRepository.findById(idArticle).get();
            //  article parent , promotion child // on peut travailler avec cascade
            article.getPromotions().add(p);
            articleRepository.save(article);
        }


}


