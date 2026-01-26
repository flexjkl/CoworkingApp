package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.input.SpaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.SpaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.entities.jpa.places.Space;
import dev.vorstu.coworkingapp.entities.jpa.users.Owner;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface SpaceMapper {

    SpaceOutputDTO toDTO(Space space);

    List<SpaceOutputDTO> listToDTO(List<Space> spaces);

    SlimSpaceOutputDTO toSlimDTO(Space space);

    List<SlimSpaceOutputDTO> listToSlimDTO(List<Space> spaces);

    Space updateEntity(@MappingTarget Space space,
                       SpaceUpdateDTO spaceUpdateDTO
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "owner", source = "owner")
    @Mapping(target = "title", source = "creationDTO.title")
    @Mapping(target = "rating", ignore = true)
    @Mapping(target = "places", ignore = true)
    @Mapping(target = "reviews", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "pricePlans", ignore = true)
    Space toEntity(
            SpaceCreationDTO creationDTO,
            Owner owner
    );
}
