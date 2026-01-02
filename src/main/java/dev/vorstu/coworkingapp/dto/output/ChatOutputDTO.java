package dev.vorstu.coworkingapp.dto.output;

import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ChatOutputDTO {

    private Long id;

    private SlimBookingOutputDTO booking;

}
