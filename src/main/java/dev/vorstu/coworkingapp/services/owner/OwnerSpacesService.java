package dev.vorstu.coworkingapp.services.owner;

import dev.vorstu.coworkingapp.dto.input.SpaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.SpaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.exceptions.AccessDeniedException;
import dev.vorstu.coworkingapp.services.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OwnerSpacesService {

    private final SpaceService spaceService;

    public Page<SlimSpaceOutputDTO> getMySpaces(Long ownerId,
                                                String titleMatcher,
                                                Pageable pageable
    ) {
        return spaceService.getSpaces(ownerId, titleMatcher, pageable);
    }

    public SpaceOutputDTO getMySpace(Long spaceId, Long ownerId) {

        if(!spaceService.isSpaceOwnedByOwner(ownerId, spaceId)) {
            throw new AccessDeniedException();
        }

        return spaceService.getSpaceById(spaceId);
    }

    public SpaceCreationDTO createSpace(Long ownerId, SpaceCreationDTO spaceCreationDTO) {
        return spaceService.createSpace(ownerId, spaceCreationDTO);
    }

    public SpaceUpdateDTO updateSpace(Long spaceId, Long ownerId, SpaceUpdateDTO spaceUpdateDTO) {

        if(!spaceService.isSpaceOwnedByOwner(ownerId, spaceId)) {
            throw new AccessDeniedException();
        }

        return spaceService.updateSpace(spaceId, spaceUpdateDTO);
    }

    public Long deleteSpace(Long myId, Long spaceId) {
        if(!spaceService.isSpaceOwnedByOwner(myId, spaceId)) {
            throw new AccessDeniedException();
        }

        return spaceService.deleteSpace(spaceId);
    }

}
