package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.ReviewMapper;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private final ReviewRepository reviewRepository;

    private final ReviewMapper reviewMapper;

    public Page<ReviewOutputDTO> getPage(Long spaceId, Pageable pageable) {
        return reviewRepository.findAllBySpaceId(spaceId, pageable)
                .map(reviewMapper::toDTO);
    }

}
