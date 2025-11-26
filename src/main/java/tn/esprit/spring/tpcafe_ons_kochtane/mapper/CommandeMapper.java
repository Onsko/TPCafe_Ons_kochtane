package tn.esprit.spring.tpcafe_ons_kochtane.mapper;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.commande.CommandeRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.commande.CommandeResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Commande;

@Mapper(componentModel = "spring")
public interface CommandeMapper {
    CommandeResponse toDto(Commande commande);
    Commande toEntity(CommandeRequest request);
}
