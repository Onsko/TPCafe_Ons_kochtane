package tn.esprit.spring.tpcafe_ons_kochtane.Services.Promotion;

import tn.esprit.spring.tpcafe_ons_kochtane.dto.promotion.PromotionRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.promotion.PromotionResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Promotion;

import java.time.LocalDate;
import java.util.List;

public interface IPromotionService {

    Promotion createPromotion(Promotion promotion);

    Promotion updatePromotion(Long id, Promotion promotion);

    void deletePromotion(Long id);

    Promotion getPromotion(Long id);

    List<Promotion> getAllPromotions();

    List<Promotion> findByPourcentage(String pourcentage);

    boolean existsByPourcentage(String pourcentage);

    List<Promotion> findByDateDebutAfter(LocalDate date);

    List<Promotion> findActivePromotionsAt(LocalDate date);

    List<Promotion> findByPourcentageInPeriod(String pourcentage, LocalDate start, LocalDate end);

    List<Promotion> findValidAtDate(LocalDate date);

    List<Promotion> findWithoutEndDate();

    void affecterPromotionAArticle(long idArticle, long ID) ;

    void desaffecterPromotionAArticle(long idArticle, long idPromotion);
}