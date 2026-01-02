package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.services.ReviewsService;
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
public class ReviewController {

    private final ReviewsService reviewsService;

    @GetMapping
    public Page<ReviewOutputDTO> getReviewsBySpaceId(
            @RequestParam Long spaceId,
            @PageableDefault Pageable pageable
    )
    {
        return reviewsService.getPage(spaceId, pageable);
    }

}
