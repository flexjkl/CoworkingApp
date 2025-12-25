package dev.vorstu.coworkingapp.dto.output.mappers;

import dev.vorstu.coworkingapp.dto.output.OwnerOutputDTO;
import dev.vorstu.coworkingapp.entities.users.Owner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        SpaceOutputMapper.class,
        ChatOutputMapper.class
})
public interface OwnerOutputDTOMapper {

    OwnerOutputDTO toDTO(Owner owner);

    List<OwnerOutputDTO> listToDTO(List<Owner> owners);

}
