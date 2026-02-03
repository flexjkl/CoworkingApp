package dev.vorstu.coworkingapp.controllers.owner;

import dev.vorstu.coworkingapp.dto.input.PricePlanCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.PricePlanUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.PricePlanOutputDTO;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.services.owner.OwnerPricePlansService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/owner/my_spaces/{spaceId}/price_plans")
@RequiredArgsConstructor
@Tag(name = "Владельцы")
@SecurityRequirement(name = "JWT")
public class OwnerPricePlansController {

    private final OwnerPricePlansService ownerPricePlansService;

    @GetMapping
    public Page<PricePlanOutputDTO> getMyPricePlansBySpaceId(
            @PathVariable Long spaceId,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return ownerPricePlansService.getPricePlansBySpaceId(jwtAuthentication.getId(), spaceId, pageable);
    }

    @PostMapping
    public PricePlanCreationDTO createPricePlan(
            @PathVariable Long spaceId,
            @RequestBody PricePlanCreationDTO pricePlanCreationDTO
    ) {
        return ownerPricePlansService.createPricePlan(spaceId, pricePlanCreationDTO);
    }

    @PatchMapping("/{id}")
    public PricePlanUpdateDTO updatePricePlan(
            @PathVariable Long spaceId,
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody PricePlanUpdateDTO pricePlanUpdateDTO
    ) {
        return ownerPricePlansService.updatePricePlan(jwtAuthentication.getId(), spaceId, id, pricePlanUpdateDTO);
    }

    @DeleteMapping("/{id}")
    public Long deletePricePlan(
            @PathVariable Long spaceId,
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return ownerPricePlansService.deletePricePlan(jwtAuthentication.getId(), spaceId, id);
    }

}
