package tn.esprit.spring.tpcafe_ons_kochtane.repositories;

import tn.esprit.spring.tpcafe_ons_kochtane.entities.Article;
import tn.esprit.spring.tpcafe_ons_kochtane.enumeration.TypeArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ArticleRepository extends JpaRepository<Article, Long> {

    // 1. Trouver les articles par nom exact
    @Query("SELECT a FROM Article a WHERE a.nomArticle = :nom")
    List<Article> findByNom(@Param("nom") String nom);

    // 2. Trouver les articles par type
    @Query("SELECT a FROM Article a WHERE a.typeArticle = :type")
    List<Article> findByType(@Param("type") TypeArticle type);

    // 3. Trouver les articles par prix exact
    @Query("SELECT a FROM Article a WHERE a.prixArticle = :prix")
    List<Article> findByPrix(@Param("prix") float prix);

    // 4. Vérifier existence d’un article par nom
    @Query("SELECT COUNT(a) > 0 FROM Article a WHERE a.nomArticle = :nom")
    boolean existsByNom(@Param("nom") String nom);

    // 5. Compter les articles par type
    @Query("SELECT COUNT(a) FROM Article a WHERE a.typeArticle = :type")
    long countByType(@Param("type") TypeArticle type);

    // 6. nom contient + type
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT('%', :nom, '%')) AND a.typeArticle = :type")
    List<Article> findByNomContainsAndType(@Param("nom") String nom, @Param("type") TypeArticle type);

    // 7. prix dans une plage + types sélectionnés
    @Query("SELECT a FROM Article a WHERE a.prixArticle BETWEEN :min AND :max AND a.typeArticle IN :types")
    List<Article> findByPrixBetweenAndTypes(
            @Param("min") float min,
            @Param("max") float max,
            @Param("types") List<TypeArticle> types
    );

    // 8. nom commence par (case insensitive) + tri par prix
    @Query("SELECT a FROM Article a WHERE LOWER(a.nomArticle) LIKE LOWER(CONCAT(:prefix, '%')) ORDER BY a.prixArticle ASC")
    List<Article> findByNomStartsWithOrderByPrix(@Param("prefix") String prefix);

    // 9. article par type avec prix max
    @Query(value = "SELECT a FROM Article a WHERE a.typeArticle = :type ORDER BY a.prixArticle DESC")
    List<Article> findArticlesWithMaxPriceByType(@Param("type") TypeArticle type); // on prendra le premier dans le service

    // 10. nom ou type + tri prix DESC
    @Query("SELECT a FROM Article a WHERE a.nomArticle = :nom OR a.typeArticle = :type ORDER BY a.prixArticle DESC")
    List<Article> findByNomOrTypeOrderByPrixDesc(@Param("nom") String nom, @Param("type") TypeArticle type);

    // 11. nom commence par prefix
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT(:prefix, '%')")
    List<Article> findByNomStartsWith(@Param("prefix") String prefix);

    // 12. nom se termine par suffix
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT('%', :suffix)")
    List<Article> findByNomEndsWith(@Param("suffix") String suffix);

    // 13. Articles sans type
    @Query("SELECT a FROM Article a WHERE a.typeArticle IS NULL")
    List<Article> findWithoutType();

    // 14. Articles avec prix renseigné
    @Query("SELECT a FROM Article a WHERE a.prixArticle IS NOT NULL")
    List<Article> findWithPrix();

    // 15. Articles avec promotions actives
    @Query("SELECT a FROM Article a JOIN a.promotions p WHERE CURRENT_DATE BETWEEN p.dateDebutPromo AND p.dateFinPromo")
    List<Article> findArticlesWithActivePromotions();

    // 16. nom contient + prix dans plage
    @Query("SELECT a FROM Article a WHERE a.nomArticle LIKE CONCAT('%', :nom, '%') AND a.prixArticle BETWEEN :min AND :max")
    List<Article> findByNomContainsAndPrixBetween(
            @Param("nom") String nom,
            @Param("min") float min,
            @Param("max") float max
    );
}
