package dev.vorstu.coworkingapp.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookingOutputDTO {

    private Long id;

    private String initials;

    private CoworkingPlaceOutputDTO place;

    private Long pricePlanId;

    private Long chatId;

    private String additions;

}
