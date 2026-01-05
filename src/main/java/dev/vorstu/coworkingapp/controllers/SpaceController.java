package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.SpaceOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import dev.vorstu.coworkingapp.services.SpaceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "Пространства (публичный)",
        description = "Предоставляет клиенту публичную информацию о пространствах"
)
@RestController
@RequestMapping("api/public/spaces")
@RequiredArgsConstructor
public class SpaceController {

    private final SpaceService spaceService;

    @GetMapping
    public Page<SlimSpaceOutputDTO> getSpaces(@PageableDefault Pageable pageable) {
        return spaceService.getPage(pageable);
    }

    @GetMapping("/{id}")
    public SpaceOutputDTO getSpaceById(@PathVariable Long id) {
        return spaceService.getSpace(id);
    }
}
