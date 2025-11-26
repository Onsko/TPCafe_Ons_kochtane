package tn.esprit.spring.tpcafe_ons_kochtane.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Commande;
import tn.esprit.spring.tpcafe_ons_kochtane.Services.Commande.ICommandeService;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/commandes")
@RequiredArgsConstructor
public class CommandeRestController {

    private final ICommandeService commandeService;

    @GetMapping("/statut/{statut}")
    public List<Commande> getByStatut(@PathVariable String statut) {
        return commandeService.getCommandesByStatut(statut);
    }

    @GetMapping("/date")
    public List<Commande> getByDate(@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date) {
        return commandeService.getCommandesByExactDate(date);
    }

    @GetMapping("/count/{statut}")
    public long countByStatut(@PathVariable String statut) {
        return commandeService.countCommandesByStatut(statut);
    }

    @DeleteMapping("/delete-before")
    public void deleteOld(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date
    ) {
        commandeService.deleteOldCommandes(date);
    }

    @GetMapping("/between")
    public List<Commande> getBetweenStatus(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date start,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date end,
            @RequestParam String statut
    ) {
        return commandeService.getCommandesBetweenDatesAndStatus(start, end, statut);
    }

    @GetMapping("/total")
    public List<Commande> getTotalSupAndStatusDiff(
            @RequestParam Double total,
            @RequestParam String statut
    ) {
        return commandeService.getCommandesTotalSupAndStatusDiff(total, statut);
    }

    @GetMapping("/status-list")
    public List<Commande> getByStatutsOrdered(@RequestParam List<String> statuts) {
        return commandeService.getCommandesByStatutsOrdered(statuts);
    }

    @GetMapping("/before-total")
    public List<Commande> getBeforeAndTotal(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date,
            @RequestParam Double min,
            @RequestParam Double max
    ) {
        return commandeService.getCommandesBeforeDateAndTotalBetween(date, min, max);
    }

    @GetMapping("/status-ends")
    public List<Commande> endsWith(@RequestParam String letter) {
        return commandeService.getCommandesStatusEndsWith(letter);
    }

    @GetMapping("/without-statut")
    public List<Commande> withoutStatut() {
        return commandeService.getCommandesWithoutStatut();
    }

    @GetMapping("/total-not-null")
    public List<Commande> totalNotNull() {
        return commandeService.getCommandesWithTotalNotNull();
    }

    @GetMapping("/details-client")
    public List<Commande> detailsClient() {
        return commandeService.getCommandesWithDetailsAndClient();
    }

    @GetMapping("/top3")
    public List<Commande> top3() {
        return commandeService.getTop3Recent();
    }
}