package dev.vorstu.coworkingapp.services.client;

import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.exceptions.AccessDeniedException;
import dev.vorstu.coworkingapp.services.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientReviewsService {

    private final ReviewsService reviewsService;

    public Page<ReviewOutputDTO> getMyReviews(
            Long id,
            Long spaceId,
            Long myId,
            Integer rate,
            Pageable pageable
    ) {
        return reviewsService.getReviews(id, spaceId, myId, rate, pageable);
    }

    public ReviewCreationDTO createReview(Long myId, ReviewCreationDTO reviewCreationDTO) {
        return reviewsService.createReview(myId, reviewCreationDTO);
    }

    public ReviewUpdateDTO updateReview(Long myId, Long reviewId, ReviewUpdateDTO reviewUpdateDTO) {

        if(!reviewsService.isReviewOwnedByUser(myId, reviewId)) {
            throw new AccessDeniedException();
        }

        return reviewsService.updateReview(reviewId, reviewUpdateDTO);
    }

    public Long deleteReview(Long myId, Long reviewId) {

        if(!reviewsService.isReviewOwnedByUser(myId, reviewId)) {
            throw new AccessDeniedException();
        }

        return reviewsService.deleteReview(reviewId);
    }

}
