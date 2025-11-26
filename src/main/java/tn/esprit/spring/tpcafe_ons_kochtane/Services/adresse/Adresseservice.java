package tn.esprit.spring.tpcafe_ons_kochtane.Services.adresse;

import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Adresse.AdresseReponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Adresse;
import tn.esprit.spring.tpcafe_ons_kochtane.mapper.AdresseMapper;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.AdresseRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor

public class Adresseservice implements IAdresseService {

    private final AdresseRepository adresseRepository;

    @Override
    public List<Adresse> getByVille(String ville) {
        return adresseRepository.findByVille(ville);
    }

    @Override
    public List<Adresse> getByCodePostal(String codePostal) {
        return adresseRepository.findByCodePostal(codePostal); // ✅ String
    }

    @Override
    public long countByVille(String ville) {
        return adresseRepository.countByVille(ville);
    }

    @Override
    public void deleteByVille(String ville) {
        adresseRepository.deleteByVille(ville);
    }

    @Override
    public List<Adresse> getByVilleAndCodePostal(String ville, String codePostal) {
        return adresseRepository.findByVilleAndCodePostal(ville, codePostal); // ✅ String
    }

    @Override
    public List<Adresse> searchRue(String keyword) {
        return adresseRepository.findByRueContainingIgnoreCase(keyword);
    }

    @Override
    public List<Adresse> getByVilles(List<String> villes) {
        return adresseRepository.findByVilleIn(villes);
    }

    @Override
    public List<Adresse> getByCodePostalBetween(String min, String max) {
        return adresseRepository.findByCodePostalBetween(min, max);
    }

    @Override
    public List<Adresse> getGreaterThan(String codePostal) {
        return adresseRepository.findByCodePostalGreaterThan(codePostal);
    }

    @Override
    public List<Adresse> getGreaterThanEqual(String codePostal) {
        return adresseRepository.findByCodePostalGreaterThanEqual(codePostal);
    }

    @Override
    public List<Adresse> getLessThan(String codePostal) {
        return adresseRepository.findByCodePostalLessThan(codePostal);
    }

    @Override
    public List<Adresse> getLessThanEqual(String codePostal) {
        return adresseRepository.findByCodePostalLessThanEqual(codePostal);
    }

    @Override
    public List<Adresse> getRueStartsWithInVille(String prefix, String ville) {
        return adresseRepository.findByRueStartingWithAndVilleOrderByCodePostalAsc(prefix, ville);
    }

    @Override
    public List<Adresse> getRueStartsWith(String prefix) {
        return adresseRepository.findByRueStartingWith(prefix);
    }

    @Override
    public List<Adresse> getVilleEndsWith(String suffix) {
        return adresseRepository.findByVilleEndingWith(suffix);
    }

    @Override
    public List<Adresse> getRueNull() {
        return adresseRepository.findByRueIsNull();
    }

    @Override
    public List<Adresse> getVilleNotNull() {
        return adresseRepository.findByVilleIsNotNull();
    }
}