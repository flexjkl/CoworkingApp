package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.services.CoworkingPlaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@Tag(
        name = "Места в пространстве (публичный)",
        description = "Предоставляет клиенту публичную информацию о местах для коворкинга в пространстве"
)
@RestController
@RequestMapping("api/public/places")
@RequiredArgsConstructor
public class CoworkingPlaceController {

    private final CoworkingPlaceService coworkingPlaceService;

    @GetMapping
    public Page<SlimCoworkingPlaceOutputDTO> getCoworkingPlaces(
            @RequestParam(required = false) Long spaceId,
            @RequestParam(required = false) String titleMatcher,
            @RequestParam(required = false) Boolean free,
            @PageableDefault Pageable pageable)
    {
        return coworkingPlaceService.getPlaces(spaceId, titleMatcher, free, pageable);
    }

    @GetMapping("/{id}")
    public CoworkingPlaceOutputDTO getCoworkingPlace(@PathVariable Long id) {
        return coworkingPlaceService.getPlace(id);
    }
}
