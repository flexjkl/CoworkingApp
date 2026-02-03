package dev.vorstu.coworkingapp.controllers.client;

import dev.vorstu.coworkingapp.dto.input.BookingCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.BookingUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.services.client.ClientBookingsService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Клиенты")
@RestController
@RequestMapping("api/client/my_bookings")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT")
public class ClientBookingsController {

    private final ClientBookingsService clientBookingsService;

    @GetMapping
    public Page<SlimBookingOutputDTO> getMyBookings(
            @RequestParam(required = false) Long placeId,
            @RequestParam(required = false) Long pricePlanId,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            Pageable pageable
    ) {
        return clientBookingsService.getMyBookings(
                jwtAuthentication.getId(),
                placeId,
                pricePlanId,
                pageable
        );
    }

    @GetMapping("/{id}")
    public BookingOutputDTO getMyBooking(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return clientBookingsService.getMyBooking(jwtAuthentication.getId(), id);
    }

    @PostMapping
    public BookingCreationDTO createBooking(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody BookingCreationDTO bookingCreationDTO
    ) {
        return clientBookingsService.createBooking(jwtAuthentication.getId(), bookingCreationDTO);
    }

    @PatchMapping("/{id}")
    public BookingUpdateDTO updateBooking(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody BookingUpdateDTO bookingUpdateDTO
    ) {
        return clientBookingsService.updateBooking(jwtAuthentication.getId(), id, bookingUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Long deleteBooking(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return clientBookingsService.deleteBooking(jwtAuthentication.getId(), id);
    }

}
