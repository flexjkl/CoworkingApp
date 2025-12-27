package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.input.PersonCreationDTO;
import dev.vorstu.coworkingapp.dto.output.ClientOutputDTO;
import dev.vorstu.coworkingapp.entities.users.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        BookingMapper.class,
        ChatMapper.class,
        ReviewMapper.class
})
public interface ClientMapper {

    ClientOutputDTO toDTO(Client client);

    List<ClientOutputDTO> listToDTO(List<ClientOutputDTO> clients);

    Client toEntity(PersonCreationDTO creationDTO);
}
