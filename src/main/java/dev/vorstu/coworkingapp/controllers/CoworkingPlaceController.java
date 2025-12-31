package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.CoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimCoworkingPlaceOutputDTO;
import dev.vorstu.coworkingapp.services.CoworkingPlaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/places")
@RequiredArgsConstructor
public class CoworkingPlaceController {

    private final CoworkingPlaceService coworkingPlaceService;

    @GetMapping("/id")
    public CoworkingPlaceOutputDTO getById(@PathVariable Long id) {
        return coworkingPlaceService.getById(id);
    }

    @GetMapping
    public Page<SlimCoworkingPlaceOutputDTO> getCoworkingPlacesBySpaceId(
            @RequestParam Long spaceId,
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String sortBy)
    {
        return coworkingPlaceService.getPlacesPageBySpaceId(
                pageNumber,
                pageSize,
                sortBy,
                spaceId);
    }
}
