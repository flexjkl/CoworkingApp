package dev.vorstu.coworkingapp.dto.output.mappers;

import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.entities.communication.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentOutputMapper {

    @Mapping(source = "author.username", target = "authorUsername")
    @Mapping(source = "commentedSpace.id", target = "commentedSpaceId")
    CommentOutputDTO toDTO(Comment comment);

    List<CommentOutputDTO> listToDTO(List<Comment> comments);
}
