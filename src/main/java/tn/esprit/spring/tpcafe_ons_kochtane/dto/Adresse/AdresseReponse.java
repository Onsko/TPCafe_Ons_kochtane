package tn.esprit.spring.tpcafe_ons_kochtane.dto.Adresse;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdresseReponse {
    String rue;
    String ville;
    String postalCode;
}
