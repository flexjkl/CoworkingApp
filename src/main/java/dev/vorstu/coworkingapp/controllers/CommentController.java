package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public Page<CommentOutputDTO> getCommentsBySpaceId(
            @RequestParam Long spaceId,
            @RequestParam(required = false, defaultValue = "null") Long parentId,
            @PageableDefault Pageable pageable
    )
    {
        return commentService.getPage(spaceId, parentId, pageable);
    }
}
