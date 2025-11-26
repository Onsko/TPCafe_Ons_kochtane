package tn.esprit.spring.tpcafe_ons_kochtane.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = {"commandes", "carteFidelite"})
@ToString(exclude = {"commandes", "carteFidelite"})
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idClient;

    @Column(nullable = false)
    String nom;

    @Column(nullable = false)
    String prenom;

    @Column(nullable = true)
    LocalDate dateNaissance;

    @Column(unique = true)
    long cin; // Ajouté pour findByCin

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "adresse_id", referencedColumnName = "idAdresse")
    Adresse adresse;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    CarteFidelite carteFidelite;

    @OneToMany(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    List<Commande> commandes;

    String articleType;
}


//    @OneToOne
//    Adresse adresse;
//    @OneToOne(mappedBy = "client")
//    CarteFidelite carteFidelite;
//    @OneToMany(mappedBy = "client")
//    List<Commande> commandes;
