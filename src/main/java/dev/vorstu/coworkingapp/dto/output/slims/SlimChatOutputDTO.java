package dev.vorstu.coworkingapp.dto.output.slims;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SlimChatOutputDTO {

    private Long id;

    private SlimBookingOutputDTO booking;

}
