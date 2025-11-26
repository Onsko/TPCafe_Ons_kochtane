package tn.esprit.spring.tpcafe_ons_kochtane.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.DetailCommande;

import java.util.List;

public interface DetailCommandeRepository extends JpaRepository<DetailCommande, Long> {

    // 1. Détails par quantité exacte
    List<DetailCommande> findByQuantiteArticle(int quantiteArticle);

    // 2. Détails par sous-total exact
    List<DetailCommande> findBySousTotalDetailArticle(float sousTotal);

    // 3. Compter les détails avec plus de X quantités
    long countByQuantiteArticleGreaterThan(int quantite);

    // 4. Vérifier l'existence de détails avec un sous-total élevé
    boolean existsBySousTotalDetailArticleGreaterThan(float sousTotal);

    // 5. Quantité dans une plage et sous-total minimum
    List<DetailCommande> findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThan(
            int minQuantite, int maxQuantite, float sousTotalMin);

    // 6. Sous-total dans une plage, triés par quantité
    List<DetailCommande> findBySousTotalDetailArticleBetweenOrderByQuantiteArticle(
            float minSousTotal, float maxSousTotal);

    // 7. Sous-total après promo dans une plage
    List<DetailCommande> findByGetSousTotalDetailArticleApresPromoBetween(
            float min, float max);

    // 8. Quantité ou sous-total minimum
    List<DetailCommande> findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(
            int quantite, float sousTotalMin);

    // 9. Les 5 détails les plus chers
    List<DetailCommande> findTop5ByOrderBySousTotalDetailArticleDesc();

    // 10. Détails sans quantité renseignée
    List<DetailCommande> findByQuantiteArticleIsNull();

    // 11. Détails avec sous-total après promo renseigné
    List<DetailCommande> findByGetSousTotalDetailArticleApresPromoIsNotNull();

    // 12. Détails avec leur commande et article
    @Query("SELECT d FROM DetailCommande d JOIN FETCH d.commande JOIN FETCH d.article")
    List<DetailCommande> findWithCommandeAndArticle();
}