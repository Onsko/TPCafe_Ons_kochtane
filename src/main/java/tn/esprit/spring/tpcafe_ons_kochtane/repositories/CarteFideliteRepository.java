package tn.esprit.spring.tpcafe_ons_kochtane.repositories;

import tn.esprit.spring.tpcafe_ons_kochtane.entities.CarteFidelite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface CarteFideliteRepository extends JpaRepository<CarteFidelite, Long> {

    // 1. Trouver les cartes avec un nombre exact de points
    @Query("SELECT c FROM CarteFidelite c WHERE c.pointsAccumules = :points")
    List<CarteFidelite> findByPoints(@Param("points") int points);

    // 2. Trouver les cartes créées à une date spécifique
    @Query("SELECT c FROM CarteFidelite c WHERE c.dateCreation = :date")
    List<CarteFidelite> findByDateCreation(@Param("date") LocalDate date);

    // 3. Compter les cartes avec plus de X points
    @Query("SELECT COUNT(c) FROM CarteFidelite c WHERE c.pointsAccumules > :points")
    long countByPointsGreaterThan(@Param("points") int points);

    // 4. Supprimer les cartes créées avant une date
    @Query("DELETE FROM CarteFidelite c WHERE c.dateCreation < :date")
    void deleteByDateBefore(@Param("date") LocalDate date);

    // 5. Trouver les cartes avec des points dans une plage et créées après une date
    @Query("SELECT c FROM CarteFidelite c WHERE c.pointsAccumules BETWEEN :min AND :max AND c.dateCreation > :date")
    List<CarteFidelite> findByPointsBetweenAndDateAfter(@Param("min") int min, @Param("max") int max, @Param("date") LocalDate date);

    // 6. Trouver les cartes avec au moins X points, triées par date de création
    @Query("SELECT c FROM CarteFidelite c WHERE c.pointsAccumules >= :points ORDER BY c.dateCreation ASC")
    List<CarteFidelite> findByPointsGreaterThanEqualOrderByDateCreation(@Param("points") int points);

    // 7. Trouver les cartes créées entre deux dates
    @Query("SELECT c FROM CarteFidelite c WHERE c.dateCreation BETWEEN :start AND :end")
    List<CarteFidelite> findByDateCreationBetween(@Param("start") LocalDate start, @Param("end") LocalDate end);

    // 8. Trouver les cartes avec peu de points OU créées avant une date
    @Query("SELECT c FROM CarteFidelite c WHERE c.pointsAccumules <= :points OR c.dateCreation < :date")
    List<CarteFidelite> findByPointsLessThanEqualOrDateBefore(@Param("points") int points, @Param("date") LocalDate date);

    // 9. Trouver la carte avec le plus de points
    @Query("SELECT c FROM CarteFidelite c ORDER BY c.pointsAccumules DESC")
    List<CarteFidelite> findTopByOrderByPointsAccumulesDesc();

    // 10. Trouver les cartes sans date de création
    @Query("SELECT c FROM CarteFidelite c WHERE c.dateCreation IS NULL")
    List<CarteFidelite> findWithoutDateCreation();

    // 11. Trouver les cartes avec des points renseignés
    @Query("SELECT c FROM CarteFidelite c WHERE c.pointsAccumules IS NOT NULL")
    List<CarteFidelite> findWithPoints();

    // 12. Trouver les cartes avec leur client propriétaire (par nom et prénom)
    @Query("SELECT c FROM CarteFidelite c WHERE c.client.nom = :nom AND c.client.prenom = :prenom")
    List<CarteFidelite> findByClientNomAndPrenom(@Param("nom") String nom, @Param("prenom") String prenom);

    // 13. Top 5 cartes avec le plus de points
    @Query("SELECT c FROM CarteFidelite c ORDER BY c.pointsAccumules DESC")
    List<CarteFidelite> findTop5ByOrderByPointsAccumulesDesc();
}
