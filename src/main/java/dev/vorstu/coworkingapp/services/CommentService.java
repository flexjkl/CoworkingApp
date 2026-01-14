package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.CommentCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CommentUpdateDTO;
import dev.vorstu.coworkingapp.dto.mappers.CommentMapper;
import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.entities.communication.Comment;
import dev.vorstu.coworkingapp.exceptions.notfound.CommentNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.PersonNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.SpaceNotFoundException;
import dev.vorstu.coworkingapp.repositories.CommentRepository;
import dev.vorstu.coworkingapp.repositories.PersonRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PersonRepository personRepository;
    private final SpaceRepository spaceRepository;

    private final CommentMapper commentMapper;

    public Page<CommentOutputDTO> getComments(Long id,
                                              Long authorId,
                                              Long commentedSpaceId,
                                              Long parentId,
                                              Pageable pageable
    ) {
        return commentRepository.findAll(id, authorId, commentedSpaceId, parentId, pageable)
                .map(commentMapper::toDTO);
    }

    public CommentCreationDTO createComment(Long authorId, CommentCreationDTO commentCreationDTO) {
        Comment comment = new Comment();

        comment.setAuthor(personRepository.getReferenceById(authorId));
        comment.setCommentedSpace(spaceRepository.getReferenceById(commentCreationDTO.getCommentedSpaceId()));
        comment.setParentCommentId(commentCreationDTO.getParentCommentId());
        comment.setText(commentCreationDTO.getMessage());

        commentRepository.save(comment);

        return commentCreationDTO;
    }

    @Transactional
    public CommentUpdateDTO updateComment(Long id, CommentUpdateDTO commentUpdateDTO) {
        Comment comment = commentRepository.findById(id)
                          .orElseThrow(CommentNotFoundException::new);

        comment.setText(commentUpdateDTO.getText());

        return commentUpdateDTO;
    }

    public Long deleteComment(Long id) {

        commentRepository.deleteById(id);

        return id;
    }
}
