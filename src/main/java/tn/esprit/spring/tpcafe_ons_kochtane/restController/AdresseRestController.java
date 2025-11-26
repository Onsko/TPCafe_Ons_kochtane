package tn.esprit.spring.tpcafe_ons_kochtane.restController;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Adresse;
import tn.esprit.spring.tpcafe_ons_kochtane.Services.adresse.IAdresseService;
import lombok.AllArgsConstructor;
import lombok.Setter;

import java.util.List;
@RestController
@RequestMapping("adresse")
@AllArgsConstructor

public class AdresseRestController {

    private final IAdresseService adresseService;

    // 🔹 Get by Ville
    @GetMapping("/ville/{ville}")
    public List<Adresse> getByVille(@PathVariable String ville) {
        return adresseService.getByVille(ville);
    }

    // 🔹 Get by Code Postal
    @GetMapping("/code-postal/{code}")
    public List<Adresse> getByCodePostal(@PathVariable String code) {
        return adresseService.getByCodePostal(code);
    }

    // 🔹 Count by Ville
    @GetMapping("/count/{ville}")
    public long countByVille(@PathVariable String ville) {
        return adresseService.countByVille(ville);
    }

    // 🔹 Delete by Ville
    @DeleteMapping("/ville/{ville}")
    public void deleteByVille(@PathVariable String ville) {
        adresseService.deleteByVille(ville);
    }

    // 🔹 Get by Ville and Code Postal
    @GetMapping("/ville-cp/{ville}/{cp}")
    public List<Adresse> getByVilleAndCP(@PathVariable String ville, @PathVariable String cp) {
        return adresseService.getByVilleAndCodePostal(ville, cp);
    }

    // 🔹 Search Rue
    @GetMapping("/rue/search/{keyword}")
    public List<Adresse> searchRue(@PathVariable String keyword) {
        return adresseService.searchRue(keyword);
    }

    // 🔹 Get by Villes (POST with list)
    @PostMapping("/villes")
    public List<Adresse> getByVilles(@RequestBody List<String> villes) {
        return adresseService.getByVilles(villes);
    }

    // 🔹 Get by Code Postal between min and max
    @GetMapping("/code-postal/between")
    public List<Adresse> getByCodePostalBetween(@RequestParam String min, @RequestParam String max) {
        return adresseService.getByCodePostalBetween(min, max);
    }

    // 🔹 Get greater than
    @GetMapping("/code-postal/gt/{code}")
    public List<Adresse> getGreaterThan(@PathVariable String code) {
        return adresseService.getGreaterThan(code);
    }

    // 🔹 Get greater than or equal
    @GetMapping("/code-postal/gte/{code}")
    public List<Adresse> getGreaterThanEqual(@PathVariable String code) {
        return adresseService.getGreaterThanEqual(code);
    }

    // 🔹 Get less than
    @GetMapping("/code-postal/lt/{code}")
    public List<Adresse> getLessThan(@PathVariable String code) {
        return adresseService.getLessThan(code);
    }

    // 🔹 Get less than or equal
    @GetMapping("/code-postal/lte/{code}")
    public List<Adresse> getLessThanEqual(@PathVariable String code) {
        return adresseService.getLessThanEqual(code);
    }

    // 🔹 Get Rue starting with prefix in a Ville
    @GetMapping("/rue/prefix/{prefix}/ville/{ville}")
    public List<Adresse> getRueStartsWithInVille(@PathVariable String prefix, @PathVariable String ville) {
        return adresseService.getRueStartsWithInVille(prefix, ville);
    }

    // 🔹 Get Rue starting with prefix
    @GetMapping("/rue/prefix/{prefix}")
    public List<Adresse> getRueStartsWith(@PathVariable String prefix) {
        return adresseService.getRueStartsWith(prefix);
    }

    // 🔹 Get Ville ending with suffix
    @GetMapping("/ville/suffix/{suffix}")
    public List<Adresse> getVilleEndsWith(@PathVariable String suffix) {
        return adresseService.getVilleEndsWith(suffix);
    }

    // 🔹 Get Rue null
    @GetMapping("/rue/null")
    public List<Adresse> getRueNull() {
        return adresseService.getRueNull();
    }

    // 🔹 Get Ville not null
    @GetMapping("/ville/not-null")
    public List<Adresse> getVilleNotNull() {
        return adresseService.getVilleNotNull();
    }
}