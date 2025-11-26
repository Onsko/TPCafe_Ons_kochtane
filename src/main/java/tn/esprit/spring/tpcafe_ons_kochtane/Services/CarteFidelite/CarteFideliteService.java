package tn.esprit.spring.tpcafe_ons_kochtane.Services.CarteFidelite;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.cartefidelite.CarteFideliteRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.cartefidelite.CarteFideliteResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.CarteFidelite;
import tn.esprit.spring.tpcafe_ons_kochtane.mapper.CarteFideliteMapper;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.CarteFideliteRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@AllArgsConstructor
public class CarteFideliteService implements ICarteFideliteService {

    private final CarteFideliteRepository carteRepository;
    private final CarteFideliteMapper carteMapper;

    // ---------------- CRUD de base ----------------
    @Override
    public CarteFideliteResponse addCarteFidelite(CarteFideliteRequest request) {
        CarteFidelite entity = carteMapper.toEntity(request);
        CarteFidelite saved = carteRepository.save(entity);
        return carteMapper.toDto(saved);
    }

    @Override
    public List<CarteFideliteResponse> saveCarteFidelites(List<CarteFideliteRequest> requests) {
        List<CarteFidelite> entities = requests.stream().map(carteMapper::toEntity).toList();
        List<CarteFidelite> saved = carteRepository.saveAll(entities);
        return saved.stream().map(carteMapper::toDto).toList();
    }

    @Override
    public CarteFideliteResponse selectCarteFideliteById(long id) {
        CarteFidelite carte = carteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Carte non trouvée"));
        return carteMapper.toDto(carte);
    }

    @Override
    public List<CarteFideliteResponse> selectAllCarteFidelites() {
        return carteRepository.findAll().stream().map(carteMapper::toDto).toList();
    }

    @Override
    public void deleteCarteFideliteById(long id) {
        carteRepository.deleteById(id);
    }

    @Override
    public void deleteAllCarteFidelites() {
        carteRepository.deleteAll();
    }

    @Override
    public long countingCarteFidelites() {
        return carteRepository.count();
    }

    @Override
    public boolean verifyCarteFideliteById(long id) {
        return carteRepository.existsById(id);
    }

    @Override
    public boolean deleteCarteFidelite(Long id) {
        if (carteRepository.existsById(id)) {
            carteRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // ---------------- Méthodes JPQL avancées ----------------
    public List<CarteFideliteResponse> findByPoints(int points) {
        return carteRepository.findByPoints(points).stream().map(carteMapper::toDto).toList();
    }

    public List<CarteFideliteResponse> findByDateCreation(LocalDate date) {
        return carteRepository.findByDateCreation(date).stream().map(carteMapper::toDto).toList();
    }

    public long countByPointsGreaterThan(int points) {
        return carteRepository.countByPointsGreaterThan(points);
    }

    public void deleteByDateBefore(LocalDate date) {
        carteRepository.deleteByDateBefore(date);
    }

    public List<CarteFideliteResponse> findByPointsBetweenAndDateAfter(int min, int max, LocalDate date) {
        return carteRepository.findByPointsBetweenAndDateAfter(min, max, date).stream()
                .map(carteMapper::toDto).toList();
    }

    public List<CarteFideliteResponse> findByPointsGreaterThanEqualOrderByDateCreation(int points) {
        return carteRepository.findByPointsGreaterThanEqualOrderByDateCreation(points).stream()
                .map(carteMapper::toDto).toList();
    }

    public List<CarteFideliteResponse> findByDateCreationBetween(LocalDate start, LocalDate end) {
        return carteRepository.findByDateCreationBetween(start, end).stream().map(carteMapper::toDto).toList();
    }

    public List<CarteFideliteResponse> findByPointsLessThanEqualOrDateBefore(int points, LocalDate date) {
        return carteRepository.findByPointsLessThanEqualOrDateBefore(points, date).stream()
                .map(carteMapper::toDto).toList();
    }

    public CarteFideliteResponse findTopByOrderByPointsAccumulesDesc() {
        return carteRepository.findTopByOrderByPointsAccumulesDesc().stream()
                .findFirst().map(carteMapper::toDto)
                .orElse(null);
    }

    public List<CarteFideliteResponse> findTop5ByOrderByPointsAccumulesDesc() {
        return carteRepository.findTop5ByOrderByPointsAccumulesDesc().stream()
                .map(carteMapper::toDto).toList();
    }

    public List<CarteFideliteResponse> findWithoutDateCreation() {
        return carteRepository.findWithoutDateCreation().stream().map(carteMapper::toDto).toList();
    }

    public List<CarteFideliteResponse> findWithPoints() {
        return carteRepository.findWithPoints().stream().map(carteMapper::toDto).toList();
    }

    public List<CarteFideliteResponse> findByClientNomAndPrenom(String nom, String prenom) {
        return carteRepository.findByClientNomAndPrenom(nom, prenom).stream()
                .map(carteMapper::toDto).toList();
    }
}
