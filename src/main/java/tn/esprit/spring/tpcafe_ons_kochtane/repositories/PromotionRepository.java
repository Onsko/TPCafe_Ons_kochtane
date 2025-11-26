package tn.esprit.spring.tpcafe_ons_kochtane.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Promotion;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {

    // Trouver les promotions par pourcentage exact
    List<Promotion> findByPourcentagePromo(String pourcentage);

    // Vérifier existence d'une promotion par pourcentage
    boolean existsByPourcentagePromo(String pourcentage);

    // Promotions débutant après une date
    List<Promotion> findByDateDebutPromoAfter(LocalDate date);

    // Promotions actives à une date donnée
    @Query("SELECT p FROM Promotion p WHERE :date BETWEEN p.dateDebutPromo AND p.dateFinPromo")
    List<Promotion> findActivePromotionsAt(@Param("date") LocalDate date);

    // Promotions avec un pourcentage spécifique débutant dans une période
    @Query("SELECT p FROM Promotion p WHERE p.pourcentagePromo = :pourcentage AND p.dateDebutPromo BETWEEN :start AND :end")
    List<Promotion> findByPourcentageInPeriod(@Param("pourcentage") String pourcentage,
                                              @Param("start") LocalDate start,
                                              @Param("end") LocalDate end);

    // Promotions valides à une date spécifique
    List<Promotion> findByDateDebutPromoBeforeAndDateFinPromoAfter(LocalDate start, LocalDate end);

    // Promotions sans date de fin
    List<Promotion> findByDateFinPromoIsNull();
}