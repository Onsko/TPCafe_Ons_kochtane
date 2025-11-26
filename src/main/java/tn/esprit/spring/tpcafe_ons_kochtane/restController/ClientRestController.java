package tn.esprit.spring.tpcafe_ons_kochtane.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Adresse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Client;
import tn.esprit.spring.tpcafe_ons_kochtane.Services.Client.IClientService;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Commande;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/client")
@RequiredArgsConstructor
public class ClientRestController {

    private final IClientService clientService;

    // ================== ANCIENNES MÉTHODES ==================

    // 🔹 CRUD
    @GetMapping
    public List<Client> getAllClients() {
        return clientService.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientService.getClientById(id);
    }

    @PostMapping
    public Client addClient(@RequestBody Client client) {
        return clientService.addClient(client);
    }

    @PutMapping
    public Client updateClient(@RequestBody Client client) {
        return clientService.updateClient(client);
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) {
        clientService.deleteClient(id);
    }

    // 🔹 Recherche par nom, prénom ou mots clés
    @GetMapping("/search")
    public List<Client> searchClients(@RequestParam String keyword) {
        return clientService.searchByNomOrPrenom(keyword);
    }

    @GetMapping("/nom/{nom}")
    public List<Client> getClientsByNom(@PathVariable String nom) {
        return clientService.findByNom(nom);
    }

    @GetMapping("/prenom/{prenom}")
    public List<Client> getClientsByPrenom(@PathVariable String prenom) {
        return clientService.findByPrenom(prenom);
    }

    @GetMapping("/nom-prenom")
    public Client getClientByNomAndPrenom(@RequestParam String nom, @RequestParam String prenom) {
        return clientService.findByNomAndPrenom(nom, prenom);
    }

    // 🔹 Statistiques
    @GetMapping("/exists/nom/{nom}")
    public boolean existsByNom(@PathVariable String nom) {
        return clientService.existsByNom(nom);
    }

    @GetMapping("/count/after")
    public long countByDateNaissanceAfter(@RequestParam String date) {
        LocalDate d = LocalDate.parse(date);
        return clientService.countByDateNaissanceAfter(d);
    }

    // 🔹 Recherche avancée
    @GetMapping("/nom-and-prenom")
    public List<Client> findByNomAndPrenomContains(@RequestParam String nom, @RequestParam String prenom) {
        return clientService.findByNomContainsAndPrenomContains(nom, prenom);
    }

    @GetMapping("/date-naissance/between")
    public List<Client> findByDateNaissanceBetween(@RequestParam String start, @RequestParam String end) {
        LocalDate dStart = LocalDate.parse(start);
        LocalDate dEnd = LocalDate.parse(end);
        return clientService.findByDateNaissanceBetween(dStart, dEnd);
    }

    @GetMapping("/nom-starting-with")
    public List<Client> findByNomStartingWithAndDateBefore(@RequestParam String prefix, @RequestParam String date) {
        LocalDate d = LocalDate.parse(date);
        return clientService.findByNomStartingWithAndDateNaissanceBefore(prefix, d);
    }

    @GetMapping("/ville/{ville}")
    public List<Client> findByAdresseVille(@PathVariable String ville) {
        return clientService.findByAdresseVille(ville);
    }

    @GetMapping("/nom-asc")
    public List<Client> findByNomContainsOrderByPrenomAsc(@RequestParam String chaine) {
        return clientService.findByNomContainsOrderByPrenomAsc(chaine);
    }

    @GetMapping("/nom-desc")
    public List<Client> findByNomContainsOrderByPrenomDesc(@RequestParam String chaine) {
        return clientService.findByNomContainsOrderByPrenomDesc(chaine);
    }

    @GetMapping("/nom-starts")
    public List<Client> findByNomStartingWith(@RequestParam String lettre) {
        return clientService.findByNomStartingWith(lettre);
    }

    @GetMapping("/prenom-ends")
    public List<Client> findByPrenomEndingWith(@RequestParam String suffix) {
        return clientService.findByPrenomEndingWith(suffix);
    }

    @GetMapping("/date-naissance/null")
    public List<Client> findByDateNaissanceIsNull() {
        return clientService.findByDateNaissanceIsNull();
    }

    @GetMapping("/adresse/not-null")
    public List<Client> findByAdresseIsNotNull() {
        return clientService.findByAdresseIsNotNull();
    }

    @GetMapping("/ville-in")
    public List<Client> findByAdresseVilleIn(@RequestParam List<String> villes) {
        return clientService.findByAdresseVilleIn(villes);
    }

    // 🔹 Carte fidélité
    @GetMapping("/fidelite/gt/{points}")
    public List<Client> findByCarteFidelitePointsGreaterThan(@PathVariable int points) {
        return clientService.findByCarteFidelitePointsAccumulesGreaterThan(points);
    }

    @GetMapping("/fidelite/gte/{points}")
    public List<Client> findByCarteFidelitePointsGreaterThanEqual(@PathVariable int points) {
        return clientService.findByCarteFidelitePointsAccumulesGreaterThanEqual(points);
    }

    @GetMapping("/fidelite/between")
    public List<Client> findByCarteFidelitePointsBetween(@RequestParam int min, @RequestParam int max) {
        return clientService.findByCarteFidelitePointsAccumulesBetween(min, max);
    }

    // 🔹 Article / Commande
    @GetMapping("/article/{type}")
    public List<Client> findClientsByArticleType(@PathVariable String type) {
        return clientService.findClientsByArticleType(type);
    }

    @GetMapping("/article-search")
    public List<Client> findClientsByNomContainsAndArticleType(@RequestParam String nom, @RequestParam String type) {
        return clientService.findClientsByNomContainsAndArticleType(nom, type);
    }

    // ================== NOUVELLES MÉTHODES ==================
    // ------------------- AFFECTATIONS -------------------
    @PutMapping("/affecter-adresse")
    public String affecterAdresse(@RequestParam String rue, @RequestParam long cin) {
        return clientService.affecterAdresseAClient(rue, cin);
    }

    @PutMapping("/affecter-carte")
    public void affecterCarte(@RequestParam long idCarte, @RequestParam long idClient) {
        clientService.affecterCarteAClient(idCarte, idClient);
    }

    @PutMapping("/affecter-commande")
    public void affecterCommande(@RequestParam long idCommande, @RequestParam long idClient) {
        clientService.affecterCommandeAClient(idCommande, idClient);
    }

    @PutMapping("/affecter-commande-by-info")
    public void affecterCommandeByInfo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateCommande,
            @RequestParam String nomClient,
            @RequestParam String prenomClient
    ) {
        clientService.affecterCommandeAClient(dateCommande, nomClient, prenomClient);
    }

    @PutMapping("/desaffecter-commande")
    public void desaffecterCommande(@RequestParam long idCommande) {
        clientService.desaffecterClientDeCommande(idCommande);
    }

    // ------------------- CASCADE -------------------
    @PostMapping("/ajouter-client-et-carte")
    public Client ajouterClientEtCarte(@RequestBody Client client) {
        return clientService.ajouterClientEtCarteFidelite(client);
    }

    // Ajouter une commande et l'affecter à un client par nom et prénom
    @PostMapping("/ajouter-commande-a-client")
    public String ajouterCommandeEtAffecter(
            @RequestBody Commande commande,
            @RequestParam String nomClient,
            @RequestParam String prenomClient
    ) {
        clientService.ajouterCommandeEtAffecterAClient(commande, nomClient, prenomClient);
        return "Commande ajoutée et affectée au client " + nomClient + " " + prenomClient;
    }

    // Ajouter et affecter une adresse à un client existant
    @PostMapping("/ajouter-adresse-a-client")
    public String ajouterAdresseEtAffecter(
            @RequestBody Adresse adresse,
            @RequestParam String nomClient,
            @RequestParam String prenomClient
    ) {
        clientService.ajouterAdresseEtAffecterAClient(adresse, nomClient, prenomClient);
        return "Adresse ajoutée et affectée au client " + nomClient + " " + prenomClient;
    }


}
