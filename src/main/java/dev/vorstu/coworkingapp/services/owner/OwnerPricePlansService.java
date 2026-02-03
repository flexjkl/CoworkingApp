package dev.vorstu.coworkingapp.services.owner;

import dev.vorstu.coworkingapp.dto.input.PricePlanCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.PricePlanUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.PricePlanOutputDTO;
import dev.vorstu.coworkingapp.exceptions.AccessDeniedException;
import dev.vorstu.coworkingapp.services.PricePlanService;
import dev.vorstu.coworkingapp.services.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerPricePlansService {

    private final SpaceService spaceService;
    private final PricePlanService pricePlanService;

    public Page<PricePlanOutputDTO> getPricePlansBySpaceId(Long myId, Long spaceId, Pageable pageable) {
        if(!spaceService.isSpaceOwnedByOwner(myId, spaceId)) {
            throw new AccessDeniedException();
        }

        return pricePlanService.getPricePlansBySpaceId(spaceId, pageable);
    }

    public PricePlanCreationDTO createPricePlan(Long spaceId, PricePlanCreationDTO pricePlanCreationDTO) {
        return pricePlanService.createPricePlan(spaceId, pricePlanCreationDTO);
    }

    public PricePlanUpdateDTO updatePricePlan(Long ownerId, Long spaceId, Long pricePlanId, PricePlanUpdateDTO pricePlanUpdateDTO) {
        if(!spaceService.isSpaceOwnedByOwner(ownerId, spaceId)) {
            throw new AccessDeniedException();
        }

        return pricePlanService.updatePricePlan(pricePlanId, pricePlanUpdateDTO);
    }

    public Long deletePricePlan(Long ownerId, Long spaceId, Long pricePlanId) {
        if(!spaceService.isSpaceOwnedByOwner(ownerId, spaceId)) {
            throw new AccessDeniedException();
        }

        return pricePlanService.deletePricePlan(pricePlanId);
    }

}
