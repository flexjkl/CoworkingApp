package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.input.CommentCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CommentUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.entities.communication.Comment;
import dev.vorstu.coworkingapp.entities.places.Space;
import dev.vorstu.coworkingapp.entities.users.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {

    @Mapping(source = "author.username", target = "authorUsername")
    @Mapping(source = "commentedSpace.id", target = "commentedSpaceId")
    CommentOutputDTO toDTO(Comment comment);

    List<CommentOutputDTO> listToDTO(List<Comment> comments);

    Comment updateEntity(
            @MappingTarget Comment comment,
            CommentUpdateDTO updateDTO
    );

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "author", source = "author")
    @Mapping(target = "commentedSpace", source = "commentedSpace")
    @Mapping(target = "text", source = "creationDTO.message")
    @Mapping(target = "parentCommentId", source = "creationDTO.parentCommentId")
    @Mapping(target = "createdAt", ignore = true)
    Comment createEntity(
            CommentCreationDTO creationDTO,
            Person author,
            Space commentedSpace
    );
}
