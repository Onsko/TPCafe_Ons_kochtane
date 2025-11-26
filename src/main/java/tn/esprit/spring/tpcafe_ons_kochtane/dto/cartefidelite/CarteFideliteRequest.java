package tn.esprit.spring.tpcafe_ons_kochtane.dto.cartefidelite;

import lombok.*;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarteFideliteRequest {
    private int pointsAccumules;
    private LocalDate dateCreation;
    private long idClient; // On lie la carte à un client existant
}
