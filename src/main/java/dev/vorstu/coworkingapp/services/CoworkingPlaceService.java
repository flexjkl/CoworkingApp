package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.CoworkingPlaceMapper;
import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.exceptions.notfound.CoworkingPlaceNotFoundException;
import dev.vorstu.coworkingapp.repositories.CoworkingPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoworkingPlaceService {

    private final CoworkingPlaceRepository coworkingPlaceRepository;

    private final CoworkingPlaceMapper coworkingPlaceMapper;

    public Page<SlimCoworkingPlaceOutputDTO> getPlaces(Long spaceId,
                                                       String titleMatcher,
                                                       Boolean free,
                                                       Pageable pageable) {
        return coworkingPlaceRepository.findAll(spaceId, titleMatcher, free, pageable)
                .map(coworkingPlaceMapper::toSlimDTO);
    }

    public CoworkingPlaceOutputDTO getPlace(Long id) {
        return coworkingPlaceMapper.toDTO(coworkingPlaceRepository.findById(id)
                .orElseThrow(CoworkingPlaceNotFoundException::new));
    }

}
