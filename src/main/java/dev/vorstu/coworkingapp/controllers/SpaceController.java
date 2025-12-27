package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.services.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/groups")
@RequiredArgsConstructor
public class SpaceController {

    private SpaceService spaceService;

    @GetMapping
    public Page<SlimSpaceOutputDTO> getSpacesPage(
            @RequestParam(required = false) int pageNumber,
            @RequestParam(required = false) int pageSize,
            @RequestParam(required = false) String sortBy)
    {
        return spaceService.getPage(pageNumber, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public SpaceOutputDTO getSpace(@PathVariable Long id) {
        return spaceService.getSpace(id);
    }
}
