package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.input.PricePlanCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.PricePlanUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.PricePlanOutputDTO;
import dev.vorstu.coworkingapp.entities.jpa.places.Space;
import dev.vorstu.coworkingapp.entities.jpa.utils.PricePlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface PricePlanMapper {

    @Mapping(source = "space.id", target = "spaceId")
    PricePlanOutputDTO toDTO(PricePlan pricePlan);

    PricePlan updateEntity(
            @MappingTarget PricePlan pricePlan,
            PricePlanUpdateDTO updateDTO
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "title", source = "creationDTO.title")
    @Mapping(target = "price", source = "creationDTO.price")
    @Mapping(target = "type", source = "creationDTO.type")
    @Mapping(target = "space", source = "space")
    PricePlan createEntity(
            PricePlanCreationDTO creationDTO,
            Space space
    );
}
