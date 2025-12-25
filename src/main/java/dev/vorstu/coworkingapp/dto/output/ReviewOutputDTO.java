package dev.vorstu.coworkingapp.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class ReviewOutputDTO {

    private Long id;

    private String reviewerUsername;

    private Long reviewedSpaceId;

    private String text;

    private Integer rate;

    private Instant createdAt;

}
