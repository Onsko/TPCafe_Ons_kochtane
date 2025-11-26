package tn.esprit.spring.tpcafe_ons_kochtane.dto.commande;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommandeRequest {
    private LocalDate dateCommande;
    private float totalCommande;
    private String statusCommande; // ou enum string
    private Long clientId; // pour référence au client
}
