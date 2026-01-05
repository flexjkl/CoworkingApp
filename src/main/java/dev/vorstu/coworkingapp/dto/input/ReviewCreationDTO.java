package dev.vorstu.coworkingapp.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewCreationDTO {

    private Long reviewerId;

    private Long reviewedSpaceId;

    private String text;

    private Integer rate;

}
