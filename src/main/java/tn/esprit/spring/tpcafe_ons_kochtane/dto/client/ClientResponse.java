package tn.esprit.spring.tpcafe_ons_kochtane.dto.client;

import lombok.*;
import java.time.LocalDate;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class ClientResponse {
    private long idClient;
    private String nom;
    private String prenom;
    private LocalDate dateNaissance;
    private long idAdresse;
}
