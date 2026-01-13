package dev.vorstu.coworkingapp.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingCreationDTO {

    private Long placeId;

    private Long pricePlanId;

    private ChatCreationDTO chatCreationDTO;

    private String additions;

}
