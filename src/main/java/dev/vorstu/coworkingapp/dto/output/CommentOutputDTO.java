package dev.vorstu.coworkingapp.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentOutputDTO {

    private Long id;

    private String authorUsername;

    private Long commentedSpaceId;

    private String text;

    private Long parentCommentId;

}
