package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.services.CommentService;
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
@RequestMapping("api/public/comments")
@RequiredArgsConstructor
@Tag(name = "Комментарии")
public class CommentController {

    private final CommentService commentService;

    @GetMapping
    public Page<CommentOutputDTO> getComments(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Long commentedSpaceId,
            @RequestParam(required = false) Long parentId,
            @PageableDefault Pageable pageable
    )
    {
        return commentService.getComments(id, authorId, commentedSpaceId, parentId, pageable);
    }
}
