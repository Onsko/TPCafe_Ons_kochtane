package tn.esprit.spring.tpcafe_ons_kochtane.Services.adresse;

import tn.esprit.spring.tpcafe_ons_kochtane.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Adresse.AdresseReponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Adresse;

import java.util.List;

public interface IAdresseService {

    List<Adresse> getByVille(String ville);
    List<Adresse> getByCodePostal(String codePostal); // ✅ String
    long countByVille(String ville);
    void deleteByVille(String ville);
    List<Adresse> getByVilleAndCodePostal(String ville, String codePostal); // ✅ String
    List<Adresse> searchRue(String keyword);
    List<Adresse> getByVilles(List<String> villes);
    List<Adresse> getByCodePostalBetween(String min, String max); // ✅ String
    List<Adresse> getGreaterThan(String codePostal);
    List<Adresse> getGreaterThanEqual(String codePostal);
    List<Adresse> getLessThan(String codePostal);
    List<Adresse> getLessThanEqual(String codePostal);
    List<Adresse> getRueStartsWithInVille(String prefix, String ville);
    List<Adresse> getRueStartsWith(String prefix);
    List<Adresse> getVilleEndsWith(String suffix);
    List<Adresse> getRueNull();
    List<Adresse> getVilleNotNull();

}