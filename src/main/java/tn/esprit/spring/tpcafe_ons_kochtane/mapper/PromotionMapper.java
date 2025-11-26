package tn.esprit.spring.tpcafe_ons_kochtane.mapper;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.promotion.PromotionRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.promotion.PromotionResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Promotion;

@Mapper(componentModel = "spring")
public interface PromotionMapper {
    PromotionResponse toDto(Promotion promotion);
    Promotion toEntity(PromotionRequest request);
}
