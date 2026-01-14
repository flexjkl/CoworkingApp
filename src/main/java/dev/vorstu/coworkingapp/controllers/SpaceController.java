package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.services.SpaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/public/spaces")
@RequiredArgsConstructor
@Tag(name = "Пространства")
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping
    public Page<SlimSpaceOutputDTO> getSpaces(
            @RequestParam(required = false) Long ownerId,
            @RequestParam(required = false) String titleMatcher,
            @PageableDefault Pageable pageable
    ) {
        return spaceService.getSpaces(
                ownerId,
                titleMatcher,
                pageable
        );
    }

    @GetMapping("/{id}")
    public SpaceOutputDTO getSpace(@PathVariable Long id) {
        return spaceService.getSpaceById(id);
    }
}
