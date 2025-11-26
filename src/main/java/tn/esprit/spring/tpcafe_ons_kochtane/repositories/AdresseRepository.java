package tn.esprit.spring.tpcafe_ons_kochtane.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Adresse;

import java.util.List;

public interface AdresseRepository extends JpaRepository<Adresse, Long> {

    List<Adresse> findByVille(String ville);
    List<Adresse> findByCodePostal(String codePostal);
    long countByVille(String ville);
    void deleteByVille(String ville);
    List<Adresse> findByVilleAndCodePostal(String ville, String codePostal);
    List<Adresse> findByRueContainingIgnoreCase(String keyword);
    List<Adresse> findByVilleIn(List<String> villes);
    List<Adresse> findByCodePostalBetween(String min, String max);
    List<Adresse> findByCodePostalGreaterThan(String codePostal);
    List<Adresse> findByCodePostalGreaterThanEqual(String codePostal);
    List<Adresse> findByCodePostalLessThan(String codePostal);
    List<Adresse> findByCodePostalLessThanEqual(String codePostal);
    List<Adresse> findByRueStartingWithAndVilleOrderByCodePostalAsc(String prefix, String ville);
    List<Adresse> findByRueStartingWith(String prefix);
    List<Adresse> findByVilleEndingWith(String suffix);
    List<Adresse> findByRueIsNull();
    List<Adresse> findByVilleIsNotNull();

    // 🔹 Méthode nécessaire pour ClientService
    Adresse findByRue(String rue);
}
