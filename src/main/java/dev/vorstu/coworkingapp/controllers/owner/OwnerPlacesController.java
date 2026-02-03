package dev.vorstu.coworkingapp.controllers.owner;

import dev.vorstu.coworkingapp.dto.input.CoworkingPlaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CoworkingPlaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.services.owner.OwnerPlacesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/owner/my_spaces/{spaceId}/places")
@RequiredArgsConstructor
@Tag(name = "Владельцы")
@SecurityRequirement(name = "JWT")
public class OwnerPlacesController {

    private final OwnerPlacesService ownerPlacesService;

    @GetMapping
    public Page<SlimCoworkingPlaceOutputDTO> getMyPlacesBySpaceId(
            @PathVariable Long spaceId,
            @RequestParam(required = false) String titleMatcher,
            @RequestParam(required = false) Boolean free,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return ownerPlacesService.getMyPlacesBySpaceId(
                spaceId,
                jwtAuthentication.getId(),
                titleMatcher,
                free,
                pageable
        );
    }

    @GetMapping("/{id}")
    public CoworkingPlaceOutputDTO getMyPlaceBySpaceId(
            @PathVariable Long spaceId,
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerPlacesService.getMyPlaceBySpaceId(spaceId, id, jwtAuthentication.getId());
    }

    @PostMapping
    public CoworkingPlaceCreationDTO createPlaceInSpace(
            @PathVariable Long spaceId,
            @RequestBody CoworkingPlaceCreationDTO coworkingPlaceCreationDTO,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerPlacesService.createPlaceInSpace(spaceId, jwtAuthentication.getId(), coworkingPlaceCreationDTO);
    }

    @PatchMapping("/{id}")
    public CoworkingPlaceUpdateDTO updatePlaceInSpace(
            @PathVariable Long spaceId,
            @PathVariable Long id,
            @RequestBody CoworkingPlaceUpdateDTO coworkingPlaceUpdateDTO,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerPlacesService.updatePlaceInSpace(spaceId, jwtAuthentication.getId(), id, coworkingPlaceUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Long deletePlaceInSpace(
            @PathVariable Long spaceId,
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerPlacesService.deletePlaceInSpace(spaceId, id, jwtAuthentication.getId());
    }

}
