package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.services.SpaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/spaces")
@RequiredArgsConstructor
public class SpaceController {

    private SpaceService spaceService;

    @GetMapping
    public Page<SlimSpaceOutputDTO> getSpaces(
            @RequestParam(required = false, defaultValue = "0") Integer pageNumber,
            @RequestParam(required = false, defaultValue = "15") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String sortBy)
    {
        return spaceService.getPage(pageNumber, pageSize, sortBy);
    }

    @GetMapping("/{id}")
    public SpaceOutputDTO getSpaceById(@PathVariable Long id) {
        return spaceService.getSpace(id);
    }
}
