package dev.vorstu.coworkingapp.dto.output;

import dev.vorstu.coworkingapp.dto.output.slims.SlimBookingOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimChatOutputDTO;

import java.util.List;

public class ClientOutputDTO extends PersonOutputDTO {

    private List<SlimBookingOutputDTO> bookings;

    private List<SlimChatOutputDTO> chats;

    private List<ReviewOutputDTO> reviews;

}
