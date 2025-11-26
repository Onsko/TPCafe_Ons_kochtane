package tn.esprit.spring.tpcafe_ons_kochtane.restController;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.DetailCommande;
import tn.esprit.spring.tpcafe_ons_kochtane.Services.DetailCommande.IDetailCommandeService;

import java.util.List;

@RestController
@RequestMapping("/detailCommande")
@AllArgsConstructor
public class DetailCommandeRestController {

    private final IDetailCommandeService detailCommandeService;

    @GetMapping("/quantite/{quantite}")
    public List<DetailCommande> getByQuantite(@PathVariable int quantite){
        return detailCommandeService.findByQuantiteArticle(quantite);
    }

    @GetMapping("/sousTotal/{sousTotal}")
    public List<DetailCommande> getBySousTotal(@PathVariable float sousTotal){
        return detailCommandeService.findBySousTotalDetailArticle(sousTotal);
    }

    @GetMapping("/countGreaterThan/{quantite}")
    public long countGreaterThan(@PathVariable int quantite){
        return detailCommandeService.countByQuantiteArticleGreaterThan(quantite);
    }

    @GetMapping("/existsSousTotalGreater/{sousTotal}")
    public boolean existsSousTotalGreater(@PathVariable float sousTotal){
        return detailCommandeService.existsBySousTotalDetailArticleGreaterThan(sousTotal);
    }

    @GetMapping("/rangeQuantityAndMinSousTotal")
    public List<DetailCommande> rangeQuantityAndMinSousTotal(
            @RequestParam int minQuantite,
            @RequestParam int maxQuantite,
            @RequestParam float sousTotalMin){
        return detailCommandeService.findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThan(minQuantite, maxQuantite, sousTotalMin);
    }

    @GetMapping("/rangeSousTotal")
    public List<DetailCommande> rangeSousTotal(
            @RequestParam float min,
            @RequestParam float max){
        return detailCommandeService.findBySousTotalDetailArticleBetweenOrderByQuantiteArticle(min, max);
    }

    @GetMapping("/rangeSousTotalApresPromo")
    public List<DetailCommande> rangeSousTotalApresPromo(
            @RequestParam float min,
            @RequestParam float max){
        return detailCommandeService.findByGetSousTotalDetailArticleApresPromoBetween(min, max);
    }

    @GetMapping("/quantiteOrSousTotal")
    public List<DetailCommande> quantiteOrSousTotal(
            @RequestParam int quantite,
            @RequestParam float sousTotalMin){
        return detailCommandeService.findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(quantite, sousTotalMin);
    }

    @GetMapping("/top5")
    public List<DetailCommande> top5(){
        return detailCommandeService.findTop5ByOrderBySousTotalDetailArticleDesc();
    }

    @GetMapping("/quantiteIsNull")
    public List<DetailCommande> quantiteIsNull(){
        return detailCommandeService.findByQuantiteArticleIsNull();
    }

    @GetMapping("/sousTotalApresPromoNotNull")
    public List<DetailCommande> sousTotalApresPromoNotNull(){
        return detailCommandeService.findByGetSousTotalDetailArticleApresPromoIsNotNull();
    }

    @GetMapping("/withCommandeAndArticle")
    public List<DetailCommande> withCommandeAndArticle(){
        return detailCommandeService.findWithCommandeAndArticle();
    }
}