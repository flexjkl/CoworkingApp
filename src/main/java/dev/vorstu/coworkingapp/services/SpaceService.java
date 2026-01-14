package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.SpaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.SpaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.mappers.SpaceMapper;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.entities.places.Space;
import dev.vorstu.coworkingapp.exceptions.notfound.OwnerNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.SpaceNotFoundException;
import dev.vorstu.coworkingapp.repositories.OwnerRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;
    private final OwnerRepository ownerRepository;

    private final SpaceMapper spaceMapper;

    public Page<SlimSpaceOutputDTO> getSpaces(
            Long ownerId,
            String titleMatcher,
            Pageable pageable
    ) {
        return spaceRepository.findAll(ownerId, titleMatcher, pageable)
                .map(spaceMapper::toSlimDTO);
    }

    public SpaceOutputDTO getSpaceById(Long id) {
        return spaceMapper.toDTO(spaceRepository.findByIdWithOwner(id)
                .orElseThrow(SpaceNotFoundException::new));
    }

    public SpaceOutputDTO createSpace(Long ownerId, SpaceCreationDTO spaceCreationDTO) {

        Space space = new Space();

        space.setTitle(spaceCreationDTO.getTitle());

        space.setOwner(ownerRepository.getReferenceById(ownerId));

        spaceRepository.save(space);

        return spaceMapper.toDTO(space);
    }

    @Transactional
    public SpaceOutputDTO updateSpace(Long id, SpaceUpdateDTO spaceUpdateDTO) {

        Space space = spaceRepository.findById(id)
                      .orElseThrow(SpaceNotFoundException::new);

        space.setTitle(spaceUpdateDTO.getTitle());

        return spaceMapper.toDTO(space);
    }

    public Long deleteSpace(Long id) {

       spaceRepository.deleteById(id);

       return id;
    }
}
