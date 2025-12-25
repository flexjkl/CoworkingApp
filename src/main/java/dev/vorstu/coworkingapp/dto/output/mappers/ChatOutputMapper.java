package dev.vorstu.coworkingapp.dto.output.mappers;

import dev.vorstu.coworkingapp.dto.output.ChatOutputDTO;
import dev.vorstu.coworkingapp.dto.output.slims.SlimChatOutputDTO;
import dev.vorstu.coworkingapp.entities.communication.Chat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring", uses = {BookingOutputMapper.class, MessageOutputMapper.class})
public interface ChatOutputMapper {

    ChatOutputDTO toDTO(Chat chat);

    List<ChatOutputDTO> listToDTO(List<Chat> chats);

    SlimChatOutputDTO toSlimDTO(Chat chat);

    List<SlimChatOutputDTO> listToSlimDTO(List<Chat> chats);
}
