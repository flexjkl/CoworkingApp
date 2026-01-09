package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.CommentMapper;
import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

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

}
