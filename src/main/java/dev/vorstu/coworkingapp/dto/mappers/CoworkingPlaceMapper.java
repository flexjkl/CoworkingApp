package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.input.CoworkingPlaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CoworkingPlaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.entities.jpa.places.CoworkingPlace;
import dev.vorstu.coworkingapp.entities.jpa.places.Space;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoworkingPlaceMapper {

    @Mapping(source = "space.id", target = "spaceId")
    CoworkingPlaceOutputDTO toDTO(CoworkingPlace coworkingPlace);

    List<CoworkingPlaceOutputDTO> listToDTO(List<CoworkingPlace> coworkingPlaces);

    SlimCoworkingPlaceOutputDTO toSlimDTO(CoworkingPlace coworkingPlace);

    List<SlimCoworkingPlaceOutputDTO> listToSlimDTO(List<CoworkingPlace> coworkingPlaces);

    CoworkingPlace updateEntity(
            @MappingTarget CoworkingPlace coworkingPlace,
            CoworkingPlaceUpdateDTO updateDTO
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "space", source = "space")
    @Mapping(target = "title", source = "creationDTO.title")
    @Mapping(target = "description", source = "creationDTO.description")
    @Mapping(target = "booking", ignore = true)
    CoworkingPlace createEntity(
            CoworkingPlaceCreationDTO creationDTO,
            Space space
    );
}
