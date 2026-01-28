package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.dto.mappers.ReviewMapper;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.events.ReviewCreationEvent;
import dev.vorstu.coworkingapp.events.ReviewUpdateEvent;
import dev.vorstu.coworkingapp.exceptions.notfound.ReviewNotFoundException;
import dev.vorstu.coworkingapp.repositories.ClientRepository;
import dev.vorstu.coworkingapp.repositories.ReviewRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private final ReviewRepository reviewRepository;
    private final SpaceRepository spaceRepository;
    private final ClientRepository clientRepository;

    private final ApplicationEventPublisher applicationEventPublisher;

    private final ReviewMapper reviewMapper;

    public Page<ReviewOutputDTO> getReviews(Long id, Long spaceId, Long reviewerId, Integer rate, Pageable pageable) {
        return reviewRepository.findAll(
                        id,
                        spaceId,
                        reviewerId,
                        rate,
                        pageable
                ).map(reviewMapper::toDTO);
    }

    @Transactional
    public ReviewCreationDTO createReview(Long reviewerId, ReviewCreationDTO reviewCreationDTO) {

        reviewRepository.save(
            reviewMapper.createEntity(
                    reviewCreationDTO,
                    clientRepository.getReferenceById(reviewerId),
                    spaceRepository.getReferenceById(reviewCreationDTO.getReviewedSpaceId())
            )
        );

        applicationEventPublisher.publishEvent(
                new ReviewCreationEvent(
                        this,
                        reviewCreationDTO.getReviewedSpaceId()
                )
        );

        return reviewCreationDTO;
    }

    @Transactional
    public ReviewUpdateDTO updateReview(Long id, ReviewUpdateDTO reviewUpdateDTO) {

        reviewMapper.updateEntity(
                reviewRepository.findById(id).orElseThrow(ReviewNotFoundException::new),
                reviewUpdateDTO
        );

        applicationEventPublisher.publishEvent(
                new ReviewUpdateEvent(
                        this,
                        id
                )
        );

        return reviewUpdateDTO;
    }

    @Transactional
    public Long deleteReview(Long id) {

        reviewRepository.deleteById(id);

        return id;
    }

    public boolean isReviewOwnedByUser(Long id, Long userId) {
        return reviewRepository.existByIdAndReviewerId(id, userId);
    }

}
