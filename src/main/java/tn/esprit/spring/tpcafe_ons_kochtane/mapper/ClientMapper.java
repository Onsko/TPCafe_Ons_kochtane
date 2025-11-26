package tn.esprit.spring.tpcafe_ons_kochtane.mapper;

import org.mapstruct.Mapper;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.client.ClientRequest;
import tn.esprit.spring.tpcafe_ons_kochtane.dto.client.ClientResponse;
import tn.esprit.spring.tpcafe_ons_kochtane.entities.Client;

@Mapper(componentModel = "spring")
public interface ClientMapper {
    ClientResponse toDto(Client client);
    Client toEntity(ClientRequest request);
}
