package tn.esprit.spring.tpcafe_ons_kochtane.entities;


import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.tpcafe_ons_kochtane.enumeration.StatusCommande;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"client", "detailCommandes"})
@ToString(exclude = {"client", "detailCommandes"})
public class Commande {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idCommande;

    LocalDate dateCommande;
    float totalCommande;

    @Enumerated(EnumType.STRING)
    StatusCommande statusCommande;

    @OneToMany(mappedBy = "commande", cascade = CascadeType.ALL)
    List<DetailCommande> detailCommandes;

    @ManyToOne
    @JoinColumn(name = "client_id") // lien vers Client
    Client client;
}
