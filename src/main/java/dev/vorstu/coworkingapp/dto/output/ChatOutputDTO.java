package dev.vorstu.coworkingapp.dto.output;

import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;

import java.util.List;

public class ChatOutputDTO {

    private Long id;

    private SlimBookingOutputDTO booking;

    private List<MessageOutputDTO> messages;

}
