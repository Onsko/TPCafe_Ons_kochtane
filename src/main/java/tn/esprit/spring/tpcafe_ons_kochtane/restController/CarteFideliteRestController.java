package tn.esprit.spring.tpcafe_ons_kochtane.restController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.cartefidelite.CarteFideliteResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.cartefidelite.CarteFideliteRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.Services.CarteFidelite.ICarteFideliteService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/cartefidelite")
@AllArgsConstructor
public class CarteFideliteRestController {

    private final ICarteFideliteService carteService;

    // ---------------- CRUD de base ----------------
    @GetMapping
    public List<CarteFideliteResponse> getAllCartes() {
        return carteService.selectAllCarteFidelites();
    }

    @GetMapping("/{id}")
    public CarteFideliteResponse getCarteById(@PathVariable long id) {
        return carteService.selectCarteFideliteById(id);
    }

    @PostMapping
    public CarteFideliteResponse addCarte(@RequestBody CarteFideliteRequest request) {
        return carteService.addCarteFidelite(request);
    }

    @PostMapping("/list")
    public List<CarteFideliteResponse> addCartes(@RequestBody List<CarteFideliteRequest> requests) {
        return carteService.saveCarteFidelites(requests);
    }

    @DeleteMapping("/{id}")
    public void deleteCarteById(@PathVariable long id) {
        carteService.deleteCarteFideliteById(id);
    }

    @DeleteMapping("/all")
    public void deleteAllCartes() {
        carteService.deleteAllCarteFidelites();
    }

    // ---------------- Méthodes JPQL avancées ----------------
    @GetMapping("/points/{points}")
    public List<CarteFideliteResponse> getByPoints(@PathVariable int points) {
        return carteService.findByPoints(points);
    }

    @GetMapping("/date/{date}")
    public List<CarteFideliteResponse> getByDateCreation(@PathVariable String date) {
        LocalDate localDate = LocalDate.parse(date);
        return carteService.findByDateCreation(localDate);
    }

    @GetMapping("/count/pointsGreater/{points}")
    public long countByPointsGreater(@PathVariable int points) {
        return carteService.countByPointsGreaterThan(points);
    }

    @DeleteMapping("/deleteBefore/{date}")
    public void deleteBeforeDate(@PathVariable String date) {
        carteService.deleteByDateBefore(LocalDate.parse(date));
    }

    @GetMapping("/pointsRangeAfterDate")
    public List<CarteFideliteResponse> getByPointsRangeAfterDate(
            @RequestParam int min,
            @RequestParam int max,
            @RequestParam String date) {
        return carteService.findByPointsBetweenAndDateAfter(min, max, LocalDate.parse(date));
    }

    @GetMapping("/pointsGreaterOrEqualOrderByDate")
    public List<CarteFideliteResponse> getByPointsGreaterOrEqualOrderByDate(@RequestParam int points) {
        return carteService.findByPointsGreaterThanEqualOrderByDateCreation(points);
    }

    @GetMapping("/dateBetween")
    public List<CarteFideliteResponse> getByDateBetween(
            @RequestParam String start,
            @RequestParam String end) {
        return carteService.findByDateCreationBetween(LocalDate.parse(start), LocalDate.parse(end));
    }

    @GetMapping("/pointsLessOrDateBefore")
    public List<CarteFideliteResponse> getByPointsLessOrDateBefore(
            @RequestParam int points,
            @RequestParam String date) {
        return carteService.findByPointsLessThanEqualOrDateBefore(points, LocalDate.parse(date));
    }

    @GetMapping("/top")
    public CarteFideliteResponse getTopCarte() {
        return carteService.findTopByOrderByPointsAccumulesDesc();
    }

    @GetMapping("/top5")
    public List<CarteFideliteResponse> getTop5Cartes() {
        return carteService.findTop5ByOrderByPointsAccumulesDesc();
    }

    @GetMapping("/withoutDate")
    public List<CarteFideliteResponse> getWithoutDateCreation() {
        return carteService.findWithoutDateCreation();
    }

    @GetMapping("/withPoints")
    public List<CarteFideliteResponse> getWithPoints() {
        return carteService.findWithPoints();
    }

    @GetMapping("/client")
    public List<CarteFideliteResponse> getByClient(
            @RequestParam String nom,
            @RequestParam String prenom) {
        return carteService.findByClientNomAndPrenom(nom, prenom);
    }
}
