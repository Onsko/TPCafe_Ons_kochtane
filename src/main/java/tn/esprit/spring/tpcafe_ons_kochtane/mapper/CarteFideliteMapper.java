package tn.esprit.spring.tpcafe_ons_kochtane.mapper;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.cartefidelite.CarteFideliteRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.cartefidelite.CarteFideliteResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.CarteFidelite;

@Mapper(componentModel = "spring")
public interface CarteFideliteMapper {
    CarteFideliteResponse toDto(CarteFidelite carte);
    CarteFidelite toEntity(CarteFideliteRequest request);
}
