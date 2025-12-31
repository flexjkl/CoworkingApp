package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.mappers.CoworkingPlaceMapper;
import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.exceptions.notfound.CoworkingPlaceNotFoundException;
import dev.vorstu.coworkingapp.repositories.CoworkingPlaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CoworkingPlaceService {

    private final CoworkingPlaceRepository coworkingPlaceRepository;

    private final CoworkingPlaceMapper coworkingPlaceMapper;

    public CoworkingPlaceOutputDTO getById(Long id) {
        return coworkingPlaceMapper.toDTO(coworkingPlaceRepository.findById(id)
                .orElseThrow(CoworkingPlaceNotFoundException::new));
    }

    public Page<SlimCoworkingPlaceOutputDTO> getPlacesPageBySpaceId(Integer pageNumber, Integer pageSize, String sortBy, Long spaceId)
    {
        return coworkingPlaceRepository.findAllBySpaceId(spaceId, PageRequest.of(pageNumber, pageSize, Sort.by(sortBy)))
                .map(coworkingPlaceMapper::toSlimDTO);
    }

}
