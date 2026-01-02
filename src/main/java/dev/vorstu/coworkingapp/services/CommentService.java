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

    public Page<CommentOutputDTO> getPage(Long spaceId, Pageable pageable) {
        return commentRepository.findAllBySpaceId(spaceId, pageable)
                .map(commentMapper::toDTO);
    }

}
