package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.CoworkingPlaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CoworkingPlaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.mappers.CoworkingPlaceMapper;
import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.entities.places.CoworkingPlace;
import dev.vorstu.coworkingapp.exceptions.notfound.CoworkingPlaceNotFoundException;
import dev.vorstu.coworkingapp.repositories.CoworkingPlaceRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoworkingPlaceService {

    private final CoworkingPlaceRepository coworkingPlaceRepository;
    private final SpaceRepository spaceRepository;

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

    public CoworkingPlaceCreationDTO createPlace(Long spaceId, CoworkingPlaceCreationDTO coworkingPlaceCreationDTO) {

        coworkingPlaceRepository.save(
                coworkingPlaceMapper.createEntity(
                        coworkingPlaceCreationDTO,
                        spaceRepository.getReferenceById(spaceId)
                )
        );

        return coworkingPlaceCreationDTO;
    }

    @Transactional
    public CoworkingPlaceUpdateDTO updatePlace(Long id, CoworkingPlaceUpdateDTO coworkingPlaceUpdateDTO) {

        coworkingPlaceMapper.updateEntity(
                coworkingPlaceRepository.findById(id).orElseThrow(CoworkingPlaceNotFoundException::new),
                coworkingPlaceUpdateDTO
        );

        return coworkingPlaceUpdateDTO;
    }

    public Long deletePlace(Long id) {

        coworkingPlaceRepository.deleteById(id);

        return id;
    }
}
