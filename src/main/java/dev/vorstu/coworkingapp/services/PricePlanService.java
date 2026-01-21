package dev.vorstu.coworkingapp.services;

import dev.vorstu.coworkingapp.dto.input.PricePlanCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.PricePlanUpdateDTO;
import dev.vorstu.coworkingapp.dto.mappers.PricePlanMapper;
import dev.vorstu.coworkingapp.dto.output.PricePlanOutputDTO;
import dev.vorstu.coworkingapp.exceptions.notfound.PricePlanNotFoundException;
import dev.vorstu.coworkingapp.repositories.PricePlanRepository;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PricePlanService {

    private final PricePlanRepository pricePlanRepository;
    private final SpaceRepository spaceRepository;

    private final PricePlanMapper pricePlanMapper;

    public Page<PricePlanOutputDTO> getPricePlansBySpaceId(Long spaceId, Pageable pageable) {
        return pricePlanRepository.findAllBySpaceId(spaceId, pageable)
                .map(pricePlanMapper::toDTO);
    }

    public PricePlanCreationDTO createPricePlan(Long spaceId, PricePlanCreationDTO pricePlanCreationDTO) {

        pricePlanRepository.save(
                pricePlanMapper.createEntity(
                        pricePlanCreationDTO,
                        spaceRepository.getReferenceById(spaceId)
                )
        );

        return pricePlanCreationDTO;
    }

    @Transactional
    public PricePlanUpdateDTO updatePricePlan(Long pricePlanId, PricePlanUpdateDTO pricePlanUpdateDTO) {

        pricePlanMapper.updateEntity(
                pricePlanRepository.findById(pricePlanId).orElseThrow(PricePlanNotFoundException::new),
                pricePlanUpdateDTO
        );

        return pricePlanUpdateDTO;
    }

    public Long deletePricePlan(Long pricePlanId) {

        pricePlanRepository.deleteById(pricePlanId);

        return pricePlanId;
    }
}
