package tn.esprit.spring.tpcafe_ons_kochtane.Services.DetailCommande;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.detailcommande.DetailCommandeRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.detailcommande.DetailCommandeResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.DetailCommande;
import tn.esprit.spring.tpcafe_ons_kochtane.mapper.DetailCommandeMapper;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.DetailCommandeRepository;

import java.util.List;
import java.util.stream.Collectors;



@Service
@AllArgsConstructor
public class DetailCommandeService implements IDetailCommandeService {

    private final DetailCommandeRepository detailCommandeRepository;

    @Override
    public List<DetailCommande> findByQuantiteArticle(int quantiteArticle) {
        return detailCommandeRepository.findByQuantiteArticle(quantiteArticle);
    }

    @Override
    public List<DetailCommande> findBySousTotalDetailArticle(float sousTotal) {
        return detailCommandeRepository.findBySousTotalDetailArticle(sousTotal);
    }

    @Override
    public long countByQuantiteArticleGreaterThan(int quantite) {
        return detailCommandeRepository.countByQuantiteArticleGreaterThan(quantite);
    }

    @Override
    public boolean existsBySousTotalDetailArticleGreaterThan(float sousTotal) {
        return detailCommandeRepository.existsBySousTotalDetailArticleGreaterThan(sousTotal);
    }

    @Override
    public List<DetailCommande> findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThan(int minQuantite, int maxQuantite, float sousTotalMin) {
        return detailCommandeRepository.findByQuantiteArticleBetweenAndSousTotalDetailArticleGreaterThan(minQuantite, maxQuantite, sousTotalMin);
    }

    @Override
    public List<DetailCommande> findBySousTotalDetailArticleBetweenOrderByQuantiteArticle(float minSousTotal, float maxSousTotal) {
        return detailCommandeRepository.findBySousTotalDetailArticleBetweenOrderByQuantiteArticle(minSousTotal, maxSousTotal);
    }

    @Override
    public List<DetailCommande> findByGetSousTotalDetailArticleApresPromoBetween(float min, float max) {
        return detailCommandeRepository.findByGetSousTotalDetailArticleApresPromoBetween(min, max);
    }

    @Override
    public List<DetailCommande> findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(int quantite, float sousTotalMin) {
        return detailCommandeRepository.findByQuantiteArticleOrSousTotalDetailArticleGreaterThan(quantite, sousTotalMin);
    }

    @Override
    public List<DetailCommande> findTop5ByOrderBySousTotalDetailArticleDesc() {
        return detailCommandeRepository.findTop5ByOrderBySousTotalDetailArticleDesc();
    }

    @Override
    public List<DetailCommande> findByQuantiteArticleIsNull() {
        return detailCommandeRepository.findByQuantiteArticleIsNull();
    }

    @Override
    public List<DetailCommande> findByGetSousTotalDetailArticleApresPromoIsNotNull() {
        return detailCommandeRepository.findByGetSousTotalDetailArticleApresPromoIsNotNull();
    }

    @Override
    public List<DetailCommande> findWithCommandeAndArticle() {
        return detailCommandeRepository.findWithCommandeAndArticle();
    }
}