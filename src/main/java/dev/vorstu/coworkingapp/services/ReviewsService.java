package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.ReviewMapper;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private final ReviewRepository reviewRepository;

    private final ReviewMapper reviewMapper;

    public Page<ReviewOutputDTO> getPage(Long spaceId, Integer page, Integer pageSize, String sortBy) {
        return reviewRepository.findAllBySpaceId(spaceId, PageRequest.of(page, pageSize, Sort.by(sortBy)))
                .map(reviewMapper::toDTO);
    }

}
