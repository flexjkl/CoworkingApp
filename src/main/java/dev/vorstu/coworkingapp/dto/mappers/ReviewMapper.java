package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.entities.jpa.communication.Review;
import dev.vorstu.coworkingapp.entities.jpa.places.Space;
import dev.vorstu.coworkingapp.entities.jpa.users.Client;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "reviewer.username", target = "reviewerUsername")
    @Mapping(source = "reviewedSpace.id", target = "reviewedSpaceId")
    ReviewOutputDTO toDTO(Review review);

    List<ReviewOutputDTO> listToDTO(List<Review> reviews);

    Review updateEntity(@MappingTarget Review review,
                        ReviewUpdateDTO updateDTO
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "reviewer", source = "reviewer")
    @Mapping(target = "reviewedSpace", source = "reviewedSpace")
    @Mapping(target = "text", source = "creationDTO.text")
    @Mapping(target = "rate", source = "creationDTO.rate")
    @Mapping(target = "createdAt", ignore = true)
    Review createEntity(ReviewCreationDTO creationDTO,
                        Client reviewer,
                        Space reviewedSpace
    );
}
