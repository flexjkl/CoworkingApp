package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.services.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/public/comments")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    public Page<CommentOutputDTO> getCommentsBySpaceId(
            @RequestParam Long spaceId,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer pageSize,
            @RequestParam(required = false, defaultValue = "") String sortBy
    )
    {
        return commentService.getPage(spaceId, page, pageSize, sortBy);
    }

}
