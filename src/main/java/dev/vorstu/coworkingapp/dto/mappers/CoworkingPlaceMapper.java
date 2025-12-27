package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.entities.places.CoworkingPlace;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CoworkingPlaceMapper {

    @Mapping(source = "space.id", target = "spaceId")
    CoworkingPlaceOutputDTO toDTO(CoworkingPlace coworkingPlace);

    List<CoworkingPlaceOutputDTO> listToDTO(List<CoworkingPlace> coworkingPlaces);

    @Mapping(source = "space.title", target = "spaceTitle")
    SlimCoworkingPlaceOutputDTO toSlimDTO(CoworkingPlace coworkingPlace);

    List<SlimCoworkingPlaceOutputDTO> listToSlimDTO(List<CoworkingPlace> coworkingPlaces);
}
