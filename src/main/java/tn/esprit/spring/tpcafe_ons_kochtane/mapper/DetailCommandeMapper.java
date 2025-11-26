package tn.esprit.spring.tpcafe_ons_kochtane.mapper;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.detailcommande.DetailCommandeRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.detailcommande.DetailCommandeResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.DetailCommande;

@Mapper(componentModel = "spring")
public interface DetailCommandeMapper {
    DetailCommandeResponse toDto(DetailCommande detailCommande);
    DetailCommande toEntity(DetailCommandeRequest request);
}
