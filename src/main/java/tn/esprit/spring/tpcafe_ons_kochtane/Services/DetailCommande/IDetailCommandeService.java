package tn.esprit.spring.tpcafe_ons_kochtane.Services.DetailCommande;

import tn.esprit.spring.tpcafe_ons_kochtane.dto.detailcommande.DetailCommandeRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.detailcommande.DetailCommandeResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.DetailCommande;

import java.util.List;

public interface IDetailCommandeService {

    List<DetailCommande> findByQuantiteArticle(int quantiteArticle);
    List<DetailCommande> findBySousTotalDetailArticle(float sousTotal);
    long countByQuantiteArticleGreaterThan(int quantite);
    boolean existsBySousTotalDetailArticleGreaterThan(float sousTotal);
    List<DetailCommande> findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThan(int minQuantite, int maxQuantite, float sousTotalMin);
    List<DetailCommande> findBySousTotalDetailArticleBetweenOrderByQuantiteArticle(float minSousTotal, float maxSousTotal);
    List<DetailCommande> findByGetSousTotalDetailArticleApresPromoBetween(float min, float max);
    List<DetailCommande> findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(int quantite, float sousTotalMin);
    List<DetailCommande> findTop5ByOrderBySousTotalDetailArticleDesc();
    List<DetailCommande> findByQuantiteArticleIsNull();
    List<DetailCommande> findByGetSousTotalDetailArticleApresPromoIsNotNull();
    List<DetailCommande> findWithCommandeAndArticle();
}
