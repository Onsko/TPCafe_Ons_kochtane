package tn.esprit.spring.tpcafe_ons_kochtane.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Client;

import java.time.LocalDate;
import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {

    // 🔹 Méthodes standards
    List<Client> findByNom(String nom);
    List<Client> findByPrenom(String prenom);
    Client findByNomAndPrenom(String nom, String prenom);
    boolean existsByNom(String nom);
    long countByDateNaissanceAfter(LocalDate date);

    // 🔹 Recherche avancée
    List<Client> findByNomContainsAndPrenomContains(String nom, String prenom);
    List<Client> findByDateNaissanceBetween(LocalDate start, LocalDate end);
    List<Client> findByNomStartingWithAndDateNaissanceBefore(String prefix, LocalDate date);
    List<Client> findByAdresseVille(String ville);
    List<Client> findByNomContainsOrderByPrenomAsc(String chaine);
    List<Client> findByNomContainsOrderByPrenomDesc(String chaine);
    List<Client> findByNomStartingWith(String lettre);
    List<Client> findByPrenomEndingWith(String suffix);
    List<Client> findByDateNaissanceIsNull();
    List<Client> findByAdresseIsNotNull();
    List<Client> findByAdresseVilleIn(List<String> villes);

    // 🔹 Carte fidélité
    List<Client> findByCarteFidelitePointsAccumulesGreaterThan(int points);
    List<Client> findByCarteFidelitePointsAccumulesGreaterThanEqual(int points);
    List<Client> findByCarteFidelitePointsAccumulesBetween(int min, int max);

    // 🔹 Article / Commande
    List<Client> findClientsByArticleType(String articleType);
    List<Client> findClientsByNomContainsAndArticleType(String nomClient, String articleType);

    // 🔹 Recherche par mot-clé
    @Query("SELECT c FROM Client c WHERE c.nom LIKE %:keyword% OR c.prenom LIKE %:keyword%")
    List<Client> findByNomOrPrenomContaining(@Param("keyword") String keyword);

    // 🔹 Recherche par CIN
    Client findByCin(long cin);
}
