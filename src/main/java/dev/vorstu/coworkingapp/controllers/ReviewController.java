package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.ReviewOutputDTO;
import dev.vorstu.coworkingapp.services.ReviewsService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private ReviewsService reviewsService;

    @GetMapping
    public Page<ReviewOutputDTO> getReviewsBySpaceId(
            @RequestParam Long spaceId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "5") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String sortBy
    )
    {
        return reviewsService.getPage(spaceId, page, pageSize, sortBy);
    }

}
