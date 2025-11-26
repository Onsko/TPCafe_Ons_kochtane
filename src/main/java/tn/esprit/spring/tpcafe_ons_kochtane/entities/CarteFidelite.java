package tn.esprit.spring.tpcafe_ons_kochtane.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode(exclude = "client")
@ToString(exclude = "client")
public class CarteFidelite {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long idCarteFidelite;

    int pointsAccumules;
    LocalDate dateCreation;

    @OneToOne
    @JoinColumn(name = "client_id") // Côté propriétaire de la relation
    Client client;
}
