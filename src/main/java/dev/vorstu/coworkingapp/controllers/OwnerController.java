package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.input.CommentCreationDTO;
import dev.vorstu.coworkingapp.dto.input.PricePlanCreationDTO;
import dev.vorstu.coworkingapp.dto.input.SpaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CommentUpdateDTO;
import dev.vorstu.coworkingapp.dto.input.update.PricePlanUpdateDTO;
import dev.vorstu.coworkingapp.dto.input.update.SpaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.dto.output.PricePlanOutputDTO;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.services.OwnerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/owner/me")
@RequiredArgsConstructor
@Tag(name = "Владельцы")
@SecurityRequirement(name = "JWT")
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/my_spaces")
    public Page<SlimSpaceOutputDTO> getMySpaces(
            @RequestParam(required = false) String titleMatcher,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return ownerService.getMySpaces(jwtAuthentication.getId(), titleMatcher, pageable);
    }

    @GetMapping("/my_spaces/{id}")
    public SpaceOutputDTO getMySpace(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerService.getMySpace(id, jwtAuthentication.getId());
    }

    @PostMapping("/my_spaces")
    public SpaceCreationDTO createSpace(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody SpaceCreationDTO spaceCreationDTO
    ) {
        return ownerService.createSpace(jwtAuthentication.getId(), spaceCreationDTO);
    }

    @PatchMapping("/my_spaces/{id}")
    public SpaceUpdateDTO updateSpace(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody SpaceUpdateDTO spaceUpdateDTO
    ) {
        return ownerService.updateSpace(id, jwtAuthentication.getId(), spaceUpdateDTO);
    }

    @DeleteMapping("/my_spaces/{id}")
    public Long deleteSpace(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerService.deleteSpace(jwtAuthentication.getId(), id);
    }



    @GetMapping("/my_spaces/{spaceId}/price_plans")
    public Page<PricePlanOutputDTO> getMyPricePlansBySpaceId(
            @PathVariable Long spaceId,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return ownerService.getPricePlansBySpaceId(jwtAuthentication.getId(), spaceId, pageable);
    }

    @PostMapping("/my_spaces/{spaceId}/price_plans")
    public PricePlanCreationDTO createPricePlan(
            @PathVariable Long spaceId,
            @RequestBody PricePlanCreationDTO pricePlanCreationDTO
    ) {
        return ownerService.createPricePlan(spaceId, pricePlanCreationDTO);
    }

    @PatchMapping("/my_spaces/{spaceId}/price_plans/{id}")
    public PricePlanUpdateDTO updatePricePlan(
            @PathVariable Long spaceId,
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody PricePlanUpdateDTO pricePlanUpdateDTO
    ) {
        return ownerService.updatePricePlan(jwtAuthentication.getId(), spaceId, id, pricePlanUpdateDTO);
    }

    @DeleteMapping("/my_spaces/{spaceId}/price_plans/{id}")
    public Long deletePricePlan(
            @PathVariable Long spaceId,
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerService.deletePricePlan(jwtAuthentication.getId(), spaceId, id);
    }



    @GetMapping("/my_comments")
    public Page<CommentOutputDTO> getMyComments(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long commentedSpaceId,
            @RequestParam(required = false) Long parentId,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return ownerService.getMyComments(
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
        return ownerService.createComment(jwtAuthentication.getId(), commentCreationDTO);
    }

    @PatchMapping("/my_comments/{id}")
    public CommentUpdateDTO updateComment(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody CommentUpdateDTO commentUpdateDTO
    ) {
        return ownerService.updateComment(jwtAuthentication.getId(), id, commentUpdateDTO);
    }

    @DeleteMapping("/my_comments/{id}")
    public Long deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerService.deleteComment(jwtAuthentication.getId(), id);
    }
}
