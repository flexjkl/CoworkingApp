package dev.vorstu.coworkingapp.dto.output;

import dev.vorstu.coworkingapp.dto.output.slims.SlimChatOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimSpaceOutputDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OwnerOutputDTO extends PersonOutputDTO {

    private List<SlimSpaceOutputDTO> spaces;

    private List<SlimChatOutputDTO> chats;

}
