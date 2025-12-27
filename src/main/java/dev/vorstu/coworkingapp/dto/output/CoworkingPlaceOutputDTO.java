package dev.vorstu.coworkingapp.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoworkingPlaceOutputDTO {

    private Long id;

    private Long spaceId;

    private String title;

    private String description;

    private boolean isFree;

}
