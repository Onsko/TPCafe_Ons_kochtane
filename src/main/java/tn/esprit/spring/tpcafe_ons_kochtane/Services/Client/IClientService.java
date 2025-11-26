package tn.esprit.spring.tpcafe_ons_kochtane.Services.Client;

import tn.esprit.spring.tpcafe_ons_kochtane.dto.client.ClientRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.client.ClientResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Adresse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Client;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Commande;

import java.util.List;
import java.time.LocalDate;

public interface IClientService {

    // 🔹 CRUD standard
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client addClient(Client client);
    Client updateClient(Client client);
    void deleteClient(Long id);

    // 🔹 Recherche
    List<Client> searchByNomOrPrenom(String keyword);
    List<Client> findByNom(String nom);
    List<Client> findByPrenom(String prenom);
    Client findByNomAndPrenom(String nom, String prenom);

    // 🔹 Statistiques
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

    // 🔹 Avec CarteFidelite
    List<Client> findByCarteFidelitePointsAccumulesGreaterThan(int points);
    List<Client> findByCarteFidelitePointsAccumulesGreaterThanEqual(int points);
    List<Client> findByCarteFidelitePointsAccumulesBetween(int min, int max);

    // 🔹 Avec Article / Commande
    List<Client> findClientsByArticleType(String articleType);
    List<Client> findClientsByNomContainsAndArticleType(String nomClient, String articleType);

    // 🔹 Méthodes spécifiques de l'étape 2
    String affecterAdresseAClient(String rue, long cin);
    void affecterCarteAClient(long idCarte, long idClient);
    void affecterCommandeAClient(long idCommande, long idClient);
    void affecterCommandeAClient(LocalDate dateCommande, String nomClient, String prenomClient);
    void desaffecterClientDeCommande(long idCommande);
    Client ajouterClientEtCarteFidelite(Client client);

    void ajouterCommandeEtAffecterAClient(Commande c, String nomClient, String prenomClient);

    void ajouterAdresseEtAffecterAClient(Adresse adresse, String nomClient, String prenomClient);
}