package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.dto.mappers.ReviewMapper;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.entities.communication.Review;
import dev.vorstu.coworkingapp.exceptions.notfound.ClientNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.ReviewNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.SpaceNotFoundException;
import dev.vorstu.coworkingapp.repositories.ClientRepository;
import dev.vorstu.coworkingapp.repositories.ReviewRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReviewsService {

    private final ReviewRepository reviewRepository;
    private final SpaceRepository spaceRepository;
    private final ClientRepository clientRepository;

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

    public ReviewOutputDTO createReview(Long reviewerId, ReviewCreationDTO reviewCreationDTO) {

        Review review = new Review();

        review.setReviewer(clientRepository.getReferenceById(reviewerId));
        review.setReviewedSpace(spaceRepository.getReferenceById(reviewCreationDTO.getReviewedSpaceId()));
        review.setText(reviewCreationDTO.getText());
        review.setRate(reviewCreationDTO.getRate());

        reviewRepository.save(review);

        return reviewMapper.toDTO(review);
    }

    @Transactional
    public ReviewOutputDTO updateReview(Long id, ReviewUpdateDTO reviewUpdateDTO) {

        Review review = reviewRepository.findById(id)
                        .orElseThrow(ReviewNotFoundException::new);

        review.setText(reviewUpdateDTO.getText());
        review.setRate(reviewUpdateDTO.getRate());

        return reviewMapper.toDTO(review);
    }

    public Long deleteReview(Long id) {

        reviewRepository.deleteById(id);

        return id;
    }

    public boolean isReviewOwnedByUser(Long id, Long userId) {
        return reviewRepository.existByIdAndReviewerId(id, userId);
    }

}
