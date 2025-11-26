package tn.esprit.spring.tpcafe_ons_kochtane.Services.CarteFidelite;

import tn.esprit.spring.tpcafe_ons_kochtane.dto.cartefidelite.CarteFideliteRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.cartefidelite.CarteFideliteResponse;

import java.time.LocalDate;
import java.util.List;

public interface ICarteFideliteService {

    // CRUD de base
    CarteFideliteResponse addCarteFidelite(CarteFideliteRequest carte);
    List<CarteFideliteResponse> saveCarteFidelites(List<CarteFideliteRequest> cartes);
    CarteFideliteResponse selectCarteFideliteById(long id);
    List<CarteFideliteResponse> selectAllCarteFidelites();
    void deleteCarteFideliteById(long id);
    void deleteAllCarteFidelites();
    long countingCarteFidelites();
    boolean verifyCarteFideliteById(long id);
    boolean deleteCarteFidelite(Long id);

    // 🔹 Méthodes JPQL avancées
    List<CarteFideliteResponse> findByPoints(int points); // cartes avec points exacts
    List<CarteFideliteResponse> findByDateCreation(LocalDate date); // cartes à une date spécifique
    long countByPointsGreaterThan(int points); // compter cartes > X points
    void deleteByDateBefore(LocalDate date); // supprimer cartes avant une date
    List<CarteFideliteResponse> findByPointsBetweenAndDateAfter(int min, int max, LocalDate date); // points dans plage et date après
    List<CarteFideliteResponse> findByPointsGreaterThanEqualOrderByDateCreation(int points); // >= points triées par date
    List<CarteFideliteResponse> findByDateCreationBetween(LocalDate start, LocalDate end); // cartes créées entre 2 dates
    List<CarteFideliteResponse> findByPointsLessThanEqualOrDateBefore(int points, LocalDate date); // points faibles OU date avant
    CarteFideliteResponse findTopByOrderByPointsAccumulesDesc(); // carte avec le plus de points
    List<CarteFideliteResponse> findTop5ByOrderByPointsAccumulesDesc(); // top 5 cartes
    List<CarteFideliteResponse> findWithoutDateCreation(); // cartes sans date
    List<CarteFideliteResponse> findWithPoints(); // cartes avec points renseignés
    List<CarteFideliteResponse> findByClientNomAndPrenom(String nom, String prenom); // cartes d’un client spécifique
}
