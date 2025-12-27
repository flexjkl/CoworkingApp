package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.entities.communication.Review;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ReviewMapper {

    @Mapping(source = "reviewer.username", target = "reviewerUsername")
    @Mapping(source = "reviewedSpace.id", target = "reviewedSpaceId")
    ReviewOutputDTO toDTO(Review review);

    List<ReviewOutputDTO> listToDTO(List<Review> reviews);

}
