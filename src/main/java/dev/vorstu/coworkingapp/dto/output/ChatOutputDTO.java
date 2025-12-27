package dev.vorstu.coworkingapp.dto.output;

import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ChatOutputDTO {

    private Long id;

    private SlimBookingOutputDTO booking;

    private List<MessageOutputDTO> messages;

}
