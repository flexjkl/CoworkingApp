package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.input.BookingCreationDTO;
import dev.vorstu.coworkingapp.dto.input.CommentCreationDTO;
import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.BookingUpdateDTO;
import dev.vorstu.coworkingapp.dto.input.update.CommentUpdateDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.BookingOutputDTO;
import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.services.ClientService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/client/me")
@RequiredArgsConstructor
@Tag(name = "Клиенты")
@SecurityRequirement(name = "JWT")
public class ClientController {

    private final ClientService clientService;

    @GetMapping("/my_reviews")
    public Page<ReviewOutputDTO> getMyReviews(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long spaceId,
            @RequestParam(required = false) Integer rate,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return clientService.getMyReviews(id, spaceId, jwtAuthentication.getId(), rate, pageable);
    }

    @PostMapping("/my_reviews")
    public ReviewOutputDTO createReview(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody ReviewCreationDTO reviewCreationDTO
    ) {
        return clientService.createReview(jwtAuthentication.getId(), reviewCreationDTO);
    }

    @PatchMapping("/my_reviews/{id}")
    public ReviewOutputDTO updateReview(
            @PathVariable Long id,
            @RequestBody ReviewUpdateDTO reviewUpdateDTO,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return clientService.updateReview(jwtAuthentication.getId(), id, reviewUpdateDTO);
    }

    @DeleteMapping("/my_reviews/{id}")
    public Long deleteReview(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return clientService.deleteReview(jwtAuthentication.getId(), id);
    }



    @GetMapping("/my_comments")
    public Page<CommentOutputDTO> getMyComments(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long commentedSpaceId,
            @RequestParam(required = false) Long parentId,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return clientService.getMyComments(
                id,
                jwtAuthentication.getId(),
                commentedSpaceId,
                parentId,
                pageable
        );
    }

    @PostMapping("/my_comments")
    public CommentCreationDTO createComment(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody CommentCreationDTO commentCreationDTO
    ) {
        return clientService.createComment(jwtAuthentication.getId(), commentCreationDTO);
    }

    @PatchMapping("/my_comments/{id}")
    public CommentUpdateDTO updateComment(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody CommentUpdateDTO commentUpdateDTO
    ) {
        return clientService.updateComment(jwtAuthentication.getId(), id, commentUpdateDTO);
    }

    @DeleteMapping("/my_comments/{id}")
    public Long deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return clientService.deleteComment(jwtAuthentication.getId(), id);
    }



    @GetMapping("/my_bookings")
    public Page<SlimBookingOutputDTO> getMyBookings(
            @RequestParam(required = false) Long placeId,
            @RequestParam(required = false) Long pricePlanId,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            Pageable pageable
    ) {
        return clientService.getMyBookings(
                jwtAuthentication.getId(),
                placeId,
                pricePlanId,
                pageable
        );
    }

    @GetMapping("/my_bookings/{id}")
    public BookingOutputDTO getMyBooking(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return clientService.getMyBooking(jwtAuthentication.getId(), id);
    }

    @PostMapping("/my_bookings")
    public BookingCreationDTO createBooking(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody BookingCreationDTO bookingCreationDTO
    ) {
        return clientService.createBooking(jwtAuthentication.getId(), bookingCreationDTO);
    }

    @PatchMapping("/my_bookings/{id}")
    public BookingUpdateDTO updateBooking(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody BookingUpdateDTO bookingUpdateDTO
    ) {
        return clientService.updateBooking(jwtAuthentication.getId(), id, bookingUpdateDTO);
    }

    @DeleteMapping("/my_bookings/{id}")
    public Long deleteBooking(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return clientService.deleteBooking(jwtAuthentication.getId(), id);
    }

}
