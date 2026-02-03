package dev.vorstu.coworkingapp.services.owner;

import dev.vorstu.coworkingapp.dto.input.CoworkingPlaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CoworkingPlaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.exceptions.AccessDeniedException;
import dev.vorstu.coworkingapp.services.CoworkingPlaceService;
import dev.vorstu.coworkingapp.services.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerPlacesService {

    private final SpaceService spaceService;
    private final CoworkingPlaceService coworkingPlaceService;

    public Page<SlimCoworkingPlaceOutputDTO> getMyPlacesBySpaceId(
            Long spaceId,
            Long myId,
            String titleMatcher,
            Boolean free,
            Pageable pageable
    ) {
        if(!spaceService.isSpaceOwnedByOwner(myId, spaceId)) {
            throw new AccessDeniedException();
        }

        return coworkingPlaceService.getPlaces(spaceId, titleMatcher, free, pageable);
    }

    public CoworkingPlaceOutputDTO getMyPlaceBySpaceId(
            Long spaceId,
            Long id,
            Long myId
    ) {
        if(!spaceService.isSpaceOwnedByOwner(myId, spaceId)) {
            throw new AccessDeniedException();
        }

        return coworkingPlaceService.getPlace(id);
    }

    public CoworkingPlaceCreationDTO createPlaceInSpace(
            Long spaceId,
            Long myId,
            CoworkingPlaceCreationDTO coworkingPlaceCreationDTO
    ) {
        if(!spaceService.isSpaceOwnedByOwner(myId, spaceId)) {
            throw new AccessDeniedException();
        }

        return coworkingPlaceService.createPlace(spaceId, coworkingPlaceCreationDTO);
    }

    public CoworkingPlaceUpdateDTO updatePlaceInSpace(
            Long spaceId,
            Long myId,
            Long id,
            CoworkingPlaceUpdateDTO coworkingPlaceUpdateDTO
    ) {
        if(!spaceService.isSpaceOwnedByOwner(myId, spaceId)) {
            throw new AccessDeniedException();
        }

        return coworkingPlaceService.updatePlace(id, coworkingPlaceUpdateDTO);
    }

    public Long deletePlaceInSpace(Long spaceId, Long id, Long myId) {
        if(!spaceService.isSpaceOwnedByOwner(myId, spaceId)) {
            throw new AccessDeniedException();
        }

        return coworkingPlaceService.deletePlace(id);
    }

}
