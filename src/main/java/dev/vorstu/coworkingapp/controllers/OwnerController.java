package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.input.SpaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.SpaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.services.OwnerService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/owner/me")
@RequiredArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/my_spaces")
    @SecurityRequirement(name = "JWT")
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
    @SecurityRequirement(name = "JWT")
    public SpaceCreationDTO createSpace(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody SpaceCreationDTO spaceCreationDTO
    ) {
        return ownerService.createSpace(jwtAuthentication.getId(), spaceCreationDTO);
    }

    @PatchMapping("/my_space/{id}")
    @SecurityRequirement(name = "JWT")
    public SpaceUpdateDTO updateSpace(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody SpaceUpdateDTO spaceUpdateDTO
    ) {
        return ownerService.updateSpace(id, jwtAuthentication.getId(), spaceUpdateDTO);
    }

    @DeleteMapping("/my_space/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteSpace(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerService.deleteSpace(jwtAuthentication.getId(), id);
    }
}
