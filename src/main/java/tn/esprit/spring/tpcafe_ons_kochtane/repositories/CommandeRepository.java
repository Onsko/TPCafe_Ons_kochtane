package tn.esprit.spring.tpcafe_ons_kochtane.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Commande;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface CommandeRepository extends JpaRepository<Commande, Long> {

    // 🔹 Méthode nécessaire pour ClientService
    Commande findByDateCommande(LocalDate date);

    // 🔹 Statut EXACT
    @Query(value = "SELECT * FROM commande c WHERE c.statut = :statut", nativeQuery = true)
    List<Commande> findByStatutSQL(@Param("statut") String statut);

    // 🔹 Date exacte
    @Query(value = "SELECT * FROM commande c WHERE DATE(c.date_commande) = :date", nativeQuery = true)
    List<Commande> findByDateSQL(@Param("date") Date date);

    // 🔹 Compter par statut
    @Query(value = "SELECT COUNT(*) FROM commande c WHERE c.statut = :statut", nativeQuery = true)
    long countByStatutSQL(@Param("statut") String statut);

    // 🔹 Supprimer avant une date
    @Query(value = "DELETE FROM commande WHERE date_commande < :date", nativeQuery = true)
    void deleteBeforeDateSQL(@Param("date") Date date);

    // 🔹 Entre deux dates + statut spécifique
    @Query(value = "SELECT * FROM commande c WHERE c.date_commande BETWEEN :start AND :end AND c.statut = :statut",
            nativeQuery = true)
    List<Commande> findBetweenDatesAndStatusSQL(
            @Param("start") Date start,
            @Param("end") Date end,
            @Param("statut") String statut
    );

    // 🔹 Total > montant + statut != paramètre
    @Query(value = "SELECT * FROM commande c WHERE c.total > :total AND c.statut <> :statut",
            nativeQuery = true)
    List<Commande> findTotalSupAndStatusDiffSQL(
            @Param("total") Double total,
            @Param("statut") String statut
    );

    // 🔹 Statuts dans une liste + triés par date
    @Query(value = "SELECT * FROM commande c WHERE c.statut IN (:statuts) ORDER BY c.date_commande ASC",
            nativeQuery = true)
    List<Commande> findByStatutsOrderedSQL(@Param("statuts") List<String> statuts);

    // 🔹 Avant une date + total dans une plage
    @Query(value = "SELECT * FROM commande c WHERE c.date_commande < :date AND c.total BETWEEN :min AND :max",
            nativeQuery = true)
    List<Commande> findBeforeDateAndTotalBetweenSQL(
            @Param("date") Date date,
            @Param("min") Double min,
            @Param("max") Double max
    );

    // 🔹 Statut finit par lettre
    @Query(value = "SELECT * FROM commande c WHERE c.statut LIKE CONCAT('%', :letter)", nativeQuery = true)
    List<Commande> findStatusEndsWithSQL(@Param("letter") String letter);

    // 🔹 Statut NULL
    @Query(value = "SELECT * FROM commande c WHERE c.statut IS NULL", nativeQuery = true)
    List<Commande> findWithoutStatutSQL();

    // 🔹 Total non NULL
    @Query(value = "SELECT * FROM commande c WHERE c.total IS NOT NULL", nativeQuery = true)
    List<Commande> findTotalNotNullSQL();

    // 🔹 Commande + Client + Détails (jointure)
    @Query(value = """
            SELECT * FROM commande c
            JOIN client cl ON c.client_id = cl.id_client
            JOIN detail_commande d ON d.commande_id = c.id_commande
            """, nativeQuery = true)
    List<Commande> findWithDetailsAndClientSQL();

    // 🔹 Top 3 commandes les plus récentes
    @Query(value = "SELECT * FROM commande c ORDER BY c.date_commande DESC LIMIT 3", nativeQuery = true)
    List<Commande> findTop3RecentSQL();
}
