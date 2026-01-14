package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.services.ReviewsService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/reviews")
@RequiredArgsConstructor
@Tag(name = "Обзоры")
public class ReviewController {

    private final ReviewsService reviewsService;

    @GetMapping
    public Page<ReviewOutputDTO> getReviews(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long spaceId,
            @RequestParam(required = false) Long reviewerId,
            @RequestParam(required = false) Integer rate,
            @PageableDefault Pageable pageable
    )
    {
        return reviewsService.getReviews(id, spaceId, reviewerId, rate, pageable);
    }

}
