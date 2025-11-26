package tn.esprit.spring.tpcafe_ons_kochtane.Services.Client;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Adresse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.CarteFidelite;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Client;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Commande;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.AdresseRepository;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.CarteFideliteRepository;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.ClientRepository;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.CommandeRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientService implements IClientService {

    private final ClientRepository clientRepository;
    private final AdresseRepository adresseRepository;
    private final CarteFideliteRepository carteFideliteRepository;
    private final CommandeRepository commandeRepository;

    // ------------------- CRUD -------------------
    @Override
    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public Client addClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);
    }

    // ------------------- Recherche simple -------------------
    @Override
    public List<Client> searchByNomOrPrenom(String keyword) {
        return clientRepository.findByNomOrPrenomContaining(keyword);
    }

    @Override
    public List<Client> findByNom(String nom) {
        return clientRepository.findByNom(nom);
    }

    @Override
    public List<Client> findByPrenom(String prenom) {
        return clientRepository.findByPrenom(prenom);
    }

    @Override
    public Client findByNomAndPrenom(String nom, String prenom) {
        return clientRepository.findByNomAndPrenom(nom, prenom);
    }

    // ------------------- Statistiques -------------------
    @Override
    public boolean existsByNom(String nom) {
        return clientRepository.existsByNom(nom);
    }

    @Override
    public long countByDateNaissanceAfter(LocalDate date) {
        return clientRepository.countByDateNaissanceAfter(date);
    }

    // ------------------- Recherche avancée -------------------
    @Override
    public List<Client> findByNomContainsAndPrenomContains(String nom, String prenom) {
        return clientRepository.findByNomContainsAndPrenomContains(nom, prenom);
    }

    @Override
    public List<Client> findByDateNaissanceBetween(LocalDate start, LocalDate end) {
        return clientRepository.findByDateNaissanceBetween(start, end);
    }

    @Override
    public List<Client> findByNomStartingWithAndDateNaissanceBefore(String prefix, LocalDate date) {
        return clientRepository.findByNomStartingWithAndDateNaissanceBefore(prefix, date);
    }

    @Override
    public List<Client> findByAdresseVille(String ville) {
        return clientRepository.findByAdresseVille(ville);
    }

    @Override
    public List<Client> findByNomContainsOrderByPrenomAsc(String chaine) {
        return clientRepository.findByNomContainsOrderByPrenomAsc(chaine);
    }

    @Override
    public List<Client> findByNomContainsOrderByPrenomDesc(String chaine) {
        return clientRepository.findByNomContainsOrderByPrenomDesc(chaine);
    }

    @Override
    public List<Client> findByNomStartingWith(String lettre) {
        return clientRepository.findByNomStartingWith(lettre);
    }

    @Override
    public List<Client> findByPrenomEndingWith(String suffix) {
        return clientRepository.findByPrenomEndingWith(suffix);
    }

    @Override
    public List<Client> findByDateNaissanceIsNull() {
        return clientRepository.findByDateNaissanceIsNull();
    }

    @Override
    public List<Client> findByAdresseIsNotNull() {
        return clientRepository.findByAdresseIsNotNull();
    }

    @Override
    public List<Client> findByAdresseVilleIn(List<String> villes) {
        return clientRepository.findByAdresseVilleIn(villes);
    }

    // ------------------- Carte fidélité -------------------
    @Override
    public List<Client> findByCarteFidelitePointsAccumulesGreaterThan(int points) {
        return clientRepository.findByCarteFidelitePointsAccumulesGreaterThan(points);
    }

    @Override
    public List<Client> findByCarteFidelitePointsAccumulesGreaterThanEqual(int points) {
        return clientRepository.findByCarteFidelitePointsAccumulesGreaterThanEqual(points);
    }

    @Override
    public List<Client> findByCarteFidelitePointsAccumulesBetween(int min, int max) {
        return clientRepository.findByCarteFidelitePointsAccumulesBetween(min, max);
    }

    // ------------------- Article / Commande -------------------
    @Override
    public List<Client> findClientsByArticleType(String articleType) {
        return clientRepository.findClientsByArticleType(articleType);
    }

    @Override
    public List<Client> findClientsByNomContainsAndArticleType(String nomClient, String articleType) {
        return clientRepository.findClientsByNomContainsAndArticleType(nomClient, articleType);
    }

    // ------------------- Méthodes afectation -------------------
    @Override
    public String affecterAdresseAClient(String rue, long cin) {
        Client c = clientRepository.findByCin(cin);
        Adresse a = adresseRepository.findByRue(rue);
        c.setAdresse(a);
        clientRepository.save(c);
        return "Adresse affectée avec succès";
    }

    @Override
    public void affecterCarteAClient(long idCarte, long idClient) {
        Client c = clientRepository.findById(idClient).orElseThrow();
        CarteFidelite cf = carteFideliteRepository.findById(idCarte).orElseThrow();
        c.setCarteFidelite(cf);
        clientRepository.save(c);
    }

    @Override
    public void affecterCommandeAClient(long idCommande, long idClient) {
        Client c = clientRepository.findById(idClient).orElseThrow();
        Commande com = commandeRepository.findById(idCommande).orElseThrow();
        com.setClient(c);
        commandeRepository.save(com);
    }

    @Override
    public void affecterCommandeAClient(LocalDate dateCommande, String nomClient, String prenomClient) {
        Client c = clientRepository.findByNomAndPrenom(nomClient, prenomClient);
        Commande com = commandeRepository.findByDateCommande(dateCommande);
        com.setClient(c);
        commandeRepository.save(com);
    }

    @Override
    public void desaffecterClientDeCommande(long idCommande) {
        Commande com = commandeRepository.findById(idCommande).orElseThrow();
        com.setClient(null);
        commandeRepository.save(com);
    }

    // ------------------- Méthode cascade ajouterClientEtCarteFidelite -------------------

    @Override
    public Client ajouterClientEtCarteFidelite(Client client)
    {
        return clientRepository.save(client); // cascade => client + carte
    }

    @Override
    public void ajouterCommandeEtAffecterAClient(Commande c, String nomClient, String prenomClient) {
    // nrecuperiw eli mawjoud :
        c= commandeRepository.save(c);
        Client client= clientRepository.findByNomAndPrenom(nomClient, prenomClient);
        // parent => commande ?? child=> client ??
        // on affecte le child au parent :
        c.setClient(client);
        commandeRepository.save(c);

    }

    @Override
    public void ajouterAdresseEtAffecterAClient(Adresse adresse, String nomClient, String prenomClient) {
        // Sauvegarde l'adresse
        Adresse savedAdresse = adresseRepository.save(adresse);

        // Récupérer le client existant
        Client client = clientRepository.findByNomAndPrenom(nomClient, prenomClient);

        // Affecter l'adresse au client
        client.setAdresse(savedAdresse);

        // Sauvegarder le client mis à jour
        clientRepository.save(client);
    }

}
