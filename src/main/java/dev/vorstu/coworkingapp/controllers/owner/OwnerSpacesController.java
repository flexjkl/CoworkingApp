package dev.vorstu.coworkingapp.controllers.owner;

import dev.vorstu.coworkingapp.dto.input.SpaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.SpaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.services.owner.OwnerSpacesService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/owner/my_spaces")
@RequiredArgsConstructor
@Tag(name = "Владельцы")
@SecurityRequirement(name = "JWT")
public class OwnerSpacesController {

    private final OwnerSpacesService ownerSpacesService;

    @GetMapping
    public Page<SlimSpaceOutputDTO> getMySpaces(
            @RequestParam(required = false) String titleMatcher,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return ownerSpacesService.getMySpaces(jwtAuthentication.getId(), titleMatcher, pageable);
    }

    @GetMapping("/{id}")
    public SpaceOutputDTO getMySpace(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerSpacesService.getMySpace(id, jwtAuthentication.getId());
    }

    @PostMapping
    public SpaceCreationDTO createSpace(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody SpaceCreationDTO spaceCreationDTO
    ) {
        return ownerSpacesService.createSpace(jwtAuthentication.getId(), spaceCreationDTO);
    }

    @PatchMapping("/{id}")
    public SpaceUpdateDTO updateSpace(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody SpaceUpdateDTO spaceUpdateDTO
    ) {
        return ownerSpacesService.updateSpace(id, jwtAuthentication.getId(), spaceUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Long deleteSpace(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerSpacesService.deleteSpace(jwtAuthentication.getId(), id);
    }

}
