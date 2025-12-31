package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.CommentMapper;
import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    private final CommentMapper commentMapper;

    public Page<CommentOutputDTO> getPage(Long spaceId, Integer page, Integer pageSize, String sortBy) {
        return commentRepository.findAllBySpaceId(spaceId, PageRequest.of(page, pageSize, Sort.by(sortBy)))
                .map(commentMapper::toDTO);
    }

}
