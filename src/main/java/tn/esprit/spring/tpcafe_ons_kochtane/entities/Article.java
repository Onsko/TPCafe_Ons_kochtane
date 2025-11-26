package tn.esprit.spring.tpcafe_ons_kochtane.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import tn.esprit.spring.tpcafe_ons_kochtane.enumeration.TypeArticle;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@EqualsAndHashCode
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // pour générer automatiquement l'ID
    long idArticle;

    String nomArticle;
    float prixArticle;

    @Enumerated(EnumType.STRING)
    TypeArticle typeArticle;

    @OneToMany(mappedBy = "article", cascade = CascadeType.ALL)
    List<DetailCommande> detailCommandes;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "article_promotion",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "promotion_id")
    )
    private List<Promotion> promotions = new ArrayList<>();

}

