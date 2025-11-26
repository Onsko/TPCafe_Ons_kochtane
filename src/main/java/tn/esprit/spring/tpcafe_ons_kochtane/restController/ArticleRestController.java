package tn.esprit.spring.tpcafe_ons_kochtane.restController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_ons_kochtane.Services.article.IArticleService;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Article.ArticleRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Article.ArticleResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Article;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Promotion;
import tn.esprit.spring.tpcafe_ons_kochtane.enumeration.TypeArticle;

import java.util.List;

@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleRestController {

    private final IArticleService articleService;

    // ================== ANCIENNES MÉTHODES - CRUD ==================

    @GetMapping
    public List<ArticleResponse> selectAllArticles() {
        return articleService.selectAllArticles();
    }

    @PostMapping
    public ArticleResponse addArticle(@RequestBody ArticleRequest articleRequest) {
        return articleService.addArticle(articleRequest);
    }

    @PostMapping("/addall")
    public List<ArticleResponse> addArticlesList(@RequestBody List<ArticleRequest> articles) {
        return articleService.saveArticles(articles);
    }

    @GetMapping("/selectByID/{id}")
    public ArticleResponse selectArticleById(@PathVariable long id) {
        return articleService.selectArticleById(id);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteArticleById(@PathVariable long id) {
        articleService.deleteArticleById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllArticles() {
        articleService.deleteAllArticles();
    }

    // ================== ANCIENNES MÉTHODES - Vérifications / Statistiques ==================

    @GetMapping("/count")
    public long countArticles() {
        return articleService.countingArticles();
    }

    @GetMapping("/exists/{id}")
    public boolean verifyArticleById(@PathVariable long id) {
        return articleService.verifyArticleById(id);
    }

    @DeleteMapping("/delete-check/{id}")
    public boolean deleteArticle(@PathVariable long id) {
        return articleService.deleteArticle(id);
    }

    // ================== NOUVELLES MÉTHODES - JPQL / Requêtes spécifiques ==================

    @GetMapping("/nom/{nom}")
    public List<ArticleResponse> findByNom(@PathVariable String nom) {
        return articleService.findByNom(nom);
    }

    @GetMapping("/type/{type}")
    public List<ArticleResponse> findByType(@PathVariable TypeArticle type) {
        return articleService.findByType(type);
    }

    @GetMapping("/prix/{prix}")
    public List<ArticleResponse> findByPrix(@PathVariable float prix) {
        return articleService.findByPrix(prix);
    }

    @GetMapping("/exists/nom/{nom}")
    public boolean existsByNom(@PathVariable String nom) {
        return articleService.existsByNom(nom);
    }

    @GetMapping("/count/type/{type}")
    public long countByType(@PathVariable TypeArticle type) {
        return articleService.countByType(type);
    }

    @GetMapping("/nom-type")
    public List<ArticleResponse> findByNomContainsAndType(@RequestParam String nom, @RequestParam TypeArticle type) {
        return articleService.findByNomContainsAndType(nom, type);
    }

    @GetMapping("/prix-between-types")
    public List<ArticleResponse> findByPrixBetweenAndTypes(
            @RequestParam float min,
            @RequestParam float max,
            @RequestParam List<TypeArticle> types
    ) {
        return articleService.findByPrixBetweenAndTypes(min, max, types);
    }

    @GetMapping("/nom-starts-order-prix")
    public List<ArticleResponse> findByNomStartsWithOrderByPrix(@RequestParam String prefix) {
        return articleService.findByNomStartsWithOrderByPrix(prefix);
    }

    @GetMapping("/max-price/type/{type}")
    public List<ArticleResponse> findArticlesWithMaxPriceByType(@PathVariable TypeArticle type) {
        return articleService.findArticlesWithMaxPriceByType(type);
    }

    @GetMapping("/nom-or-type-order-prix-desc")
    public List<ArticleResponse> findByNomOrTypeOrderByPrixDesc(@RequestParam String nom, @RequestParam TypeArticle type) {
        return articleService.findByNomOrTypeOrderByPrixDesc(nom, type);
    }

    @GetMapping("/nom-starts/{prefix}")
    public List<ArticleResponse> findByNomStartsWith(@PathVariable String prefix) {
        return articleService.findByNomStartsWith(prefix);
    }

    @GetMapping("/nom-ends/{suffix}")
    public List<ArticleResponse> findByNomEndsWith(@PathVariable String suffix) {
        return articleService.findByNomEndsWith(suffix);
    }

    @GetMapping("/without-type")
    public List<ArticleResponse> findWithoutType() {
        return articleService.findWithoutType();
    }

    @GetMapping("/with-prix")
    public List<ArticleResponse> findWithPrix() {
        return articleService.findWithPrix();
    }

    @GetMapping("/active-promotions")
    public List<ArticleResponse> findArticlesWithActivePromotions() {
        return articleService.findArticlesWithActivePromotions();
    }

    @GetMapping("/nom-prix-between")
    public List<ArticleResponse> findByNomContainsAndPrixBetween(@RequestParam String nom, @RequestParam float min, @RequestParam float max) {
        return articleService.findByNomContainsAndPrixBetween(nom, min, max);
    }

    // ================== NOUVELLES MÉTHODES - CASCADE ==================
    // Ajouter un article avec ses promotions associées
    @PostMapping("/ajouter-article-et-promotions")
    public Article ajouterArticleEtPromotions(@RequestBody Article article) {
        return articleService.ajouterArticleEtPromotions(article);
    }

    // ================== NOUVELLES MÉTHODES - Affectation / Désaffectation Promotions ==================

    // Affecter une promotion à un article
    @PutMapping("/affecter-promotion/{idArticle}/{idPromotion}")
    public String affecterPromotion(
            @PathVariable long idArticle,
            @PathVariable long idPromotion
    ) {
        articleService.affecterPromotionAArticle(idArticle, idPromotion);
        return "Promotion affectée avec succès à l'article " + idArticle;
    }

    // Désaffecter une promotion d'un article
    @PostMapping("/desaffecter-promotion/{idArticle}/{idPromotion}")
    public String desaffecterPromotion(
            @PathVariable long idArticle,
            @PathVariable long idPromotion
    ) {
        articleService.desaffecterPromotionAArticle(idArticle, idPromotion);
        return "Promotion désaffectée avec succès de l'article " + idArticle;
    }

    // Ajouter une promotion et l'affecter à un article
    @PostMapping("/ajouter-promo-a-article/{idArticle}")
    public String ajouterPromoEtAffecter(
            @PathVariable long idArticle,
            @RequestBody Promotion promotion
    ) {
        articleService.ajouterPromoEtAffecterAArticle(promotion, idArticle);
        return "Promotion ajoutée et affectée à l'article " + idArticle;
    }
}
