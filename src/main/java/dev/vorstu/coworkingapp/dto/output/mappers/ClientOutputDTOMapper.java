package dev.vorstu.coworkingapp.dto.output.mappers;

import dev.vorstu.coworkingapp.dto.output.ClientOutputDTO;
import dev.vorstu.coworkingapp.entities.users.Client;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        BookingOutputMapper.class,
        ChatOutputMapper.class,
        ReviewOutputMapper.class
})
public interface ClientOutputDTOMapper {

    ClientOutputDTO toDTO(Client client);

    List<ClientOutputDTO> listToDTO(List<ClientOutputDTO> clients);

}
