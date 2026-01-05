package dev.vorstu.coworkingapp.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoworkingPlaceCreationDTO {

    private Long spaceId;

    private String title;

    private String description;

}
