package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.BookingCreationDTO;
import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.BookingUpdateDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import dev.vorstu.coworkingapp.exceptions.accessdenied.AccessDeniedException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientService {

    private final ReviewsService reviewsService;
    private final BookingService bookingService;

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

    public Page<SlimBookingOutputDTO> getMyBookings(
            Long clientId,
            Long placeId,
            Long pricePlanId,
            Pageable pageable
    ) {
        return bookingService.getBookings(clientId, placeId, pricePlanId, pageable);
    }

    public BookingOutputDTO getMyBooking(Long myId, Long bookingId) {
        if(!bookingService.isBookingOwnedByClient(bookingId, myId)) {
            throw new AccessDeniedException();
        }

        return bookingService.getBooking(bookingId);
    }

    public BookingCreationDTO createBooking(Long clientId, BookingCreationDTO bookingCreationDTO) {
        return bookingService.createBooking(clientId, bookingCreationDTO);
    }

    public BookingUpdateDTO updateBooking(Long myId, Long id, BookingUpdateDTO updateDTO) {
        if(!bookingService.isBookingOwnedByClient(id, myId)) {
            throw new AccessDeniedException();
        }

        return bookingService.updateBooking(id, updateDTO);
    }

    public Long deleteBooking(Long myId, Long id) {
        if(!bookingService.isBookingOwnedByClient(id, myId)) {
            throw new AccessDeniedException();
        }

        return bookingService.deleteBooking(id);
    }
}
