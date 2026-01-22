package dev.vorstu.coworkingapp.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BookingOutputDTO {

    private Long id;

    private String username;

    private CoworkingPlaceOutputDTO place;

    private Long pricePlanId;

    private Long chatId;

    private String additions;

    private Instant startTime;

    private Instant endTime;

}
