package dev.vorstu.coworkingapp.services.client;

import dev.vorstu.coworkingapp.dto.input.BookingCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.BookingUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import dev.vorstu.coworkingapp.exceptions.AccessDeniedException;
import dev.vorstu.coworkingapp.services.BookingService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ClientBookingsService {

    private final BookingService bookingService;

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
