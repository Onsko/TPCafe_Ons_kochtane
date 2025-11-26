package tn.esprit.spring.tpcafe_ons_kochtane.mapper;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Adresse.AdresseReponse;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.Adresse.AdresseRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Adresse;

@Mapper(componentModel = "spring")
public interface AdresseMapper {
    AdresseReponse todto(Adresse adresse);
    Adresse toentity(AdresseRequest adresseReponse);
}
