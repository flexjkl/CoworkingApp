package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.entities.communication.Review;
import dev.vorstu.coworkingapp.entities.users.Client;
import dev.vorstu.coworkingapp.exceptions.accessdenied.AccessDeniedException;
import dev.vorstu.coworkingapp.exceptions.notfound.ClientNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.ReviewNotFoundException;
import dev.vorstu.coworkingapp.repositories.ClientRepository;
import dev.vorstu.coworkingapp.repositories.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ReviewsService reviewsService;
    private final CommentService commentService;
    private final BookingService bookingService;

    private final ReviewRepository reviewRepository;

    public Page<ReviewOutputDTO> getMyReviews(
            Long id,
            Long spaceId,
            Long myId,
            Integer rate,
            Pageable pageable
    ) {
        return reviewsService.getReviews(id, spaceId, myId, rate, pageable);
    }

    public ReviewOutputDTO createReview(Long myId, ReviewCreationDTO reviewCreationDTO) {
        return reviewsService.createReview(myId, reviewCreationDTO);
    }

    public ReviewOutputDTO updateReview(Long myId, Long reviewId, ReviewUpdateDTO reviewUpdateDTO) {

        return reviewsService.updateReview(reviewId, reviewUpdateDTO);
    }

    public Long deleteReview(Long myId, Long reviewId) {

    }
}
