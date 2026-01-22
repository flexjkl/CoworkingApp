package dev.vorstu.coworkingapp.dto.input;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class BookingCreationDTO {

    private Long placeId;

    private Long pricePlanId;

    private ChatCreationDTO chatCreationDTO;

    private String additions;

    private Instant startTime;

    private Instant endTime;
}
