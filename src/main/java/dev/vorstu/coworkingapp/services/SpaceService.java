package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.SpaceMapper;
import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.exceptions.notfound.SpaceNotFoundException;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SpaceService {

    private final SpaceRepository spaceRepository;

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
        return spaceMapper.toDTO(spaceRepository.findById(id)
                .orElseThrow(SpaceNotFoundException::new));
    }

}
