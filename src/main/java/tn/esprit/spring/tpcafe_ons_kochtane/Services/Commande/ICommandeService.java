package tn.esprit.spring.tpcafe_ons_kochtane.Services.Commande;

import tn.esprit.spring.tpcafe_ons_kochtane.entities.Commande;

import java.util.Date;
import java.util.List;

public interface ICommandeService {

    List<Commande> getCommandesByStatut(String statut);

    List<Commande> getCommandesByExactDate(Date date);

    long countCommandesByStatut(String statut);

    void deleteOldCommandes(Date date);

    List<Commande> getCommandesBetweenDatesAndStatus(Date start, Date end, String statut);

    List<Commande> getCommandesTotalSupAndStatusDiff(Double total, String statut);

    List<Commande> getCommandesByStatutsOrdered(List<String> statuts);

    List<Commande> getCommandesBeforeDateAndTotalBetween(Date date, Double min, Double max);

    List<Commande> getCommandesStatusEndsWith(String letter);

    List<Commande> getCommandesWithoutStatut();

    List<Commande> getCommandesWithTotalNotNull();

    List<Commande> getCommandesWithDetailsAndClient();

    List<Commande> getTop3Recent();
}