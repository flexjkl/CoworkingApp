package dev.vorstu.coworkingapp.controllers;

import dev.vorstu.coworkingapp.dto.input.CommentCreationDTO;
import dev.vorstu.coworkingapp.dto.input.update.CommentUpdateDTO;
import dev.vorstu.coworkingapp.dto.output.CommentOutputDTO;
import dev.vorstu.coworkingapp.jwt.JwtAuthentication;
import dev.vorstu.coworkingapp.services.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/comments")
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

    @GetMapping("/my_comments")
    @SecurityRequirement(name = "JWT")
    public Page<CommentOutputDTO> getMyComments(
            @RequestParam(required = false) Long id,
            @RequestParam(required = false) Long commentedSpaceId,
            @RequestParam(required = false) Long parentId,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @PageableDefault Pageable pageable
    ) {
        return commentService.getComments(
                id,
                jwtAuthentication.getId(),
                commentedSpaceId,
                parentId,
                pageable
        );
    }

    @PostMapping("/my_comments")
    @SecurityRequirement(name = "JWT")
    public CommentCreationDTO createComment(
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody CommentCreationDTO commentCreationDTO
    ) {
        return commentService.createComment(jwtAuthentication.getId(), commentCreationDTO);
    }

    @PatchMapping("/my_comments/{id}")
    @SecurityRequirement(name = "JWT")
    public CommentUpdateDTO updateComment(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication,
            @RequestBody CommentUpdateDTO commentUpdateDTO
    ) {
        return commentService.updateComment(jwtAuthentication.getId(), id, commentUpdateDTO);
    }

    @DeleteMapping("/my_comments/{id}")
    @SecurityRequirement(name = "JWT")
    public Long deleteComment(
            @PathVariable Long id,
            @AuthenticationPrincipal JwtAuthentication jwtAuthentication
    ) {
        return commentService.deleteComment(id, jwtAuthentication.getId());
    }
}
