package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.services.ClientService;
import dev.vorstu.coworkingapp.services.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/client/me")
@RequiredArgsConstructor
public class ClientContorller {

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

    @DeleteMapping("my_reviews/{id}")
    public Long deleteReview(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return
    }
}
