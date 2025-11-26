package tn.esprit.spring.tpcafe_ons_kochtane.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    // 🔹 Recherche par nom
    List<User> findByNom(String nom);
    List<User> findByNomIgnoreCase(String nom);
    List<User> findByNomContaining(String nom);
    List<User> findByNomContainsIgnoreCase(String nom);

    // 🔹 Recherche par prénom
    List<User> findByPrenom(String prenom);
    List<User> findByPrenomIgnoreCase(String prenom);
    List<User> findByPrenomContaining(String prenom);
    List<User> findByPrenomContainsIgnoreCase(String prenom);

    // 🔹 Recherche combinée nom + prénom
    List<User> findByNomAndPrenom(String nom, String prenom);
    List<User> findByNomOrPrenom(String nom, String prenom);

    // 🔹 Recherche par CIN
    List<User> findByCinGreaterThan(long cin);
    List<User> findByCinGreaterThanEqual(long cin);
    List<User> findByCinLessThan(long cin);
    List<User> findByCinBetween(long min, long max);
    List<User> findByCinGreaterThanEqualAndCinLessThan(long min, long max);

    // 🔹 Recherche par date de naissance
    List<User> findByDateNaissanceAfter(LocalDate date);
    List<User> findByDateNaissanceBefore(LocalDate date);
    List<User> findByDateNaissanceBetween(LocalDate start, LocalDate end);

    // 🔹 Recherche par dateAjout (java.util.Date)
    List<User> findByDateAjoutAfter(Date date);
    List<User> findByDateAjoutBefore(Date date);
    List<User> findByDateAjoutBetween(Date start, Date end);

    // 🔹 Recherche par keyword sur nom ou prénom (JPQL)
    @Query("SELECT u FROM User u WHERE u.nom LIKE %:keyword% OR u.prenom LIKE %:keyword%")
    List<User> findByNomOrPrenomContaining(@Param("keyword") String keyword);

}