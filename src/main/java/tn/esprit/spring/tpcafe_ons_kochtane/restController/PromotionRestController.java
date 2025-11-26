package tn.esprit.spring.tpcafe_ons_kochtane.restController;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Promotion;
import tn.esprit.spring.tpcafe_ons_kochtane.Services.Promotion.IPromotionService;

import java.time.LocalDate;
import java.util.List;
@RestController
@RequestMapping("/api/promotions")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PromotionRestController {

    private final IPromotionService promotionService;

    @PostMapping
    public Promotion create(@RequestBody Promotion promotion) {
        return promotionService.createPromotion(promotion);
    }

    @PutMapping("/{id}")
    public Promotion update(@PathVariable Long id, @RequestBody Promotion promotion) {
        return promotionService.updatePromotion(id, promotion);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        promotionService.deletePromotion(id);
    }

    @GetMapping("/{id}")
    public Promotion get(@PathVariable Long id) {
        return promotionService.getPromotion(id);
    }

    @GetMapping
    public List<Promotion> getAll() {
        return promotionService.getAllPromotions();
    }

    @GetMapping("/percentage/{pourcentage}")
    public List<Promotion> getByPourcentage(@PathVariable String pourcentage) {
        return promotionService.findByPourcentage(pourcentage);
    }

    @GetMapping("/active/{date}")
    public List<Promotion> getActive(@PathVariable String date) {
        return promotionService.findActivePromotionsAt(LocalDate.parse(date));
    }

    @GetMapping("/without-end-date")
    public List<Promotion> getWithoutEndDate() {
        return promotionService.findWithoutEndDate();
    }
}