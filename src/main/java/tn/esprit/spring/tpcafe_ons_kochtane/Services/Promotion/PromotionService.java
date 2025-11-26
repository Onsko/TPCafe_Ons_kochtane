package tn.esprit.spring.tpcafe_ons_kochtane.Services.Promotion;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Article;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Promotion;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.ArticleRepository;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.PromotionRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PromotionService implements IPromotionService {

    private final PromotionRepository promotionRepository;
    private final ArticleRepository articleRepository;

    @Override
    public Promotion createPromotion(Promotion promotion) {
        return promotionRepository.save(promotion);
    }

    @Override
    public Promotion updatePromotion(Long id, Promotion promotion) {
        Promotion existing = promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion non trouvée"));
        existing.setPourcentagePromo(promotion.getPourcentagePromo());
        existing.setDateDebutPromo(promotion.getDateDebutPromo());
        existing.setDateFinPromo(promotion.getDateFinPromo());
        existing.setArticles(promotion.getArticles());
        return promotionRepository.save(existing);
    }

    @Override
    public void deletePromotion(Long id) {
        promotionRepository.deleteById(id);
    }

    @Override
    public Promotion getPromotion(Long id) {
        return promotionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Promotion non trouvée"));
    }

    @Override
    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    @Override
    public List<Promotion> findByPourcentage(String pourcentage) {
        return promotionRepository.findByPourcentagePromo(pourcentage);
    }

    @Override
    public boolean existsByPourcentage(String pourcentage) {
        return promotionRepository.existsByPourcentagePromo(pourcentage);
    }

    @Override
    public List<Promotion> findByDateDebutAfter(LocalDate date) {
        return promotionRepository.findByDateDebutPromoAfter(date);
    }

    @Override
    public List<Promotion> findActivePromotionsAt(LocalDate date) {
        return promotionRepository.findActivePromotionsAt(date);
    }

    @Override
    public List<Promotion> findByPourcentageInPeriod(String pourcentage, LocalDate start, LocalDate end) {
        return promotionRepository.findByPourcentageInPeriod(pourcentage, start, end);
    }

    @Override
    public List<Promotion> findValidAtDate(LocalDate date) {
        return promotionRepository.findByDateDebutPromoBeforeAndDateFinPromoAfter(date, date);
    }

    @Override
    public List<Promotion> findWithoutEndDate() {
        return promotionRepository.findByDateFinPromoIsNull();
    }

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
}







