package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.CoworkingPlaceCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CoworkingPlaceUpdateDTO;
import dev.vorstu.coworkingapp.dto.mappers.CoworkingPlaceMapper;
import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.entities.places.CoworkingPlace;
import dev.vorstu.coworkingapp.exceptions.notfound.CoworkingPlaceNotFoundException;
import dev.vorstu.coworkingapp.exceptions.notfound.SpaceNotFoundException;
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

    public CoworkingPlaceOutputDTO createPlace(Long ownerId, CoworkingPlaceCreationDTO coworkingPlaceCreationDTO) {
        CoworkingPlace coworkingPlace = new CoworkingPlace();

        coworkingPlace.setSpace(spaceRepository.getReferenceById(coworkingPlaceCreationDTO.getSpaceId()));
        coworkingPlace.setTitle(coworkingPlaceCreationDTO.getTitle());
        coworkingPlace.setDescription(coworkingPlaceCreationDTO.getDescription());

        coworkingPlaceRepository.save(coworkingPlace);

        return coworkingPlaceMapper.toDTO(coworkingPlace);
    }

    @Transactional
    public CoworkingPlaceOutputDTO updatePlace(Long id, CoworkingPlaceUpdateDTO coworkingPlaceUpdateDTO) {
        CoworkingPlace coworkingPlace = coworkingPlaceRepository.findById(id)
                                        .orElseThrow(CoworkingPlaceNotFoundException::new);

        coworkingPlace.setTitle(coworkingPlaceUpdateDTO.getTitle());
        coworkingPlace.setDescription(coworkingPlaceUpdateDTO.getDescription());

        return coworkingPlaceMapper.toDTO(coworkingPlace);
    }

    //todo: Каскадное удаление Booking не происходит
    public Long deletePlace(Long id) {

        coworkingPlaceRepository.deleteById(id);

        return id;
    }
}
