package tn.esprit.spring.tpcafe_ons_kochtane.dto.commande;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeResponse {
    private Long idCommande;
    private LocalDate dateCommande;
    private float totalCommande;
    private String statusCommande;
    private Long clientId;
}
