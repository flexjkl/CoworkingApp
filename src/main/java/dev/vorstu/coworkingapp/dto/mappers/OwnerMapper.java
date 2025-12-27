package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.input.PersonCreationDTO;
import dev.vorstu.coworkingapp.dto.output.OwnerOutputDTO;
import dev.vorstu.coworkingapp.entities.users.Owner;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        SpaceMapper.class,
        ChatMapper.class
})
public interface OwnerMapper {

    OwnerOutputDTO toDTO(Owner owner);

    List<OwnerOutputDTO> listToDTO(List<Owner> owners);

    Owner toEntity(PersonCreationDTO creationDTO);
}
