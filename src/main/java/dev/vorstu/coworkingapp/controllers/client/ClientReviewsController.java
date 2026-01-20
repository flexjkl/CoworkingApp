package dev.vorstu.coworkingapp.controllers.client;

import dev.vorstu.coworkingapp.dto.input.ReviewCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.ReviewUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
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

@Tag(name = "Клиенты")
@RestController
@RequestMapping("api/client/my_reviews")
@RequiredArgsConstructor
@SecurityRequirement(name = "JWT")
public class ClientReviewsController {

    private final ClientService clientService;

    @GetMapping
    public Page<ReviewOutputDTO> getMyReviews(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long spaceId,
            @RequestParam(required = false) Integer rate,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return clientService.getMyReviews(id, spaceId, jwtAuthentication.getId(), rate, pageable);
    }

    @PostMapping
    public ReviewCreationDTO createReview(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody ReviewCreationDTO reviewCreationDTO
    ) {
        return clientService.createReview(jwtAuthentication.getId(), reviewCreationDTO);
    }

    @PatchMapping("/{id}")
    public ReviewUpdateDTO updateReview(
            @PathVariable Long id,
            @RequestBody ReviewUpdateDTO reviewUpdateDTO,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return clientService.updateReview(jwtAuthentication.getId(), id, reviewUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Long deleteReview(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return clientService.deleteReview(jwtAuthentication.getId(), id);
    }
}
