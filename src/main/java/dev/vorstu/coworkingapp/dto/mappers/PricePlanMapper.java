package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.output.PersonOutputDTO;
import dev.vorstu.coworkingapp.dto.output.PricePlanOutputDTO;
import dev.vorstu.coworkingapp.entities.utils.PricePlan;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PricePlanMapper {

    @Mapping(source = "space.id", target = "spaceId")
    PricePlanOutputDTO toDTO(PricePlan pricePlan);

}
