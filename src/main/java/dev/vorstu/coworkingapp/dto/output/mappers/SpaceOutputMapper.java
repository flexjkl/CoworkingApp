package dev.vorstu.coworkingapp.dto.output.mappers;

import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.entities.places.Space;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {
        PersonOutputMapper.class,
        CoworkingPlaceOutputMapper.class,
        ReviewOutputMapper.class,
        CommentOutputMapper.class
})
public interface SpaceOutputMapper {

    SpaceOutputDTO toDTO(Space space);

    List<SpaceOutputDTO> listToDTO(List<Space> spaces);

    SlimSpaceOutputDTO toSlimDTO(Space space);

    List<SlimSpaceOutputDTO> listToSlimDTO(List<Space> spaces);
}
