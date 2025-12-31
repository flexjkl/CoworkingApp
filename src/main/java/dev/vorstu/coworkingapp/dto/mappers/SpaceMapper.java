package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.entities.places.Space;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = PersonMapper.class)
public interface SpaceMapper {

    SpaceOutputDTO toDTO(Space space);

    List<SpaceOutputDTO> listToDTO(List<Space> spaces);

    SlimSpaceOutputDTO toSlimDTO(Space space);

    List<SlimSpaceOutputDTO> listToSlimDTO(List<Space> spaces);
}
