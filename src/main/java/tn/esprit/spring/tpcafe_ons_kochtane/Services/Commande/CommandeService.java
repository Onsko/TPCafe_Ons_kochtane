package tn.esprit.spring.tpcafe_ons_kochtane.Services.Commande;



import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Commande;
import tn.esprit.spring.tpcafe_ons_kochtane.repositories.CommandeRepository;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommandeService implements ICommandeService {

    private final CommandeRepository commandeRepository;

    @Override
    public List<Commande> getCommandesByStatut(String statut) {
        return commandeRepository.findByStatutSQL(statut);
    }

    @Override
    public List<Commande> getCommandesByExactDate(Date date) {
        return commandeRepository.findByDateSQL(date);
    }

    @Override
    public long countCommandesByStatut(String statut) {
        return commandeRepository.countByStatutSQL(statut);
    }

    @Override
    public void deleteOldCommandes(Date date) {
        commandeRepository.deleteBeforeDateSQL(date);
    }

    @Override
    public List<Commande> getCommandesBetweenDatesAndStatus(Date start, Date end, String statut) {
        return commandeRepository.findBetweenDatesAndStatusSQL(start, end, statut);
    }

    @Override
    public List<Commande> getCommandesTotalSupAndStatusDiff(Double total, String statut) {
        return commandeRepository.findTotalSupAndStatusDiffSQL(total, statut);
    }

    @Override
    public List<Commande> getCommandesByStatutsOrdered(List<String> statuts) {
        return commandeRepository.findByStatutsOrderedSQL(statuts);
    }

    @Override
    public List<Commande> getCommandesBeforeDateAndTotalBetween(Date date, Double min, Double max) {
        return commandeRepository.findBeforeDateAndTotalBetweenSQL(date, min, max);
    }

    @Override
    public List<Commande> getCommandesStatusEndsWith(String letter) {
        return commandeRepository.findStatusEndsWithSQL(letter);
    }

    @Override
    public List<Commande> getCommandesWithoutStatut() {
        return commandeRepository.findWithoutStatutSQL();
    }

    @Override
    public List<Commande> getCommandesWithTotalNotNull() {
        return commandeRepository.findTotalNotNullSQL();
    }

    @Override
    public List<Commande> getCommandesWithDetailsAndClient() {
        return commandeRepository.findWithDetailsAndClientSQL();
    }

    @Override
    public List<Commande> getTop3Recent() {
        return commandeRepository.findTop3RecentSQL();
    }
}
