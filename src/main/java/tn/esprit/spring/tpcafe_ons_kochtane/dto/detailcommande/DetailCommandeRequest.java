package tn.esprit.spring.tpcafe_ons_kochtane.dto.detailcommande;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DetailCommandeRequest {
    private int quantiteArticle;
    private float sousTotalDetailArticle;
    private float sousTotalDetailArticleApresPromo;
    private Long commandeId;
    private Long articleId;
}
