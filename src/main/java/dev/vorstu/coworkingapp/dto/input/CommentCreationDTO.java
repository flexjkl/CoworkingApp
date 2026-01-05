package dev.vorstu.coworkingapp.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentCreationDTO {

    private Long authorId;

    private Long commentedSpaceId;

    private String message;

    private Long parentCommentId;

}
