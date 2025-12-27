package dev.vorstu.coworkingapp.dto.mappers;

import dev.vorstu.coworkingapp.dto.output.MessageOutputDTO;
import dev.vorstu.coworkingapp.entities.communication.Message;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source = "sender.username", target = "senderUsername")
    @Mapping(source = "chat.id", target = "chatId")
    MessageOutputDTO toDTO(Message message);

    List<MessageOutputDTO> listToDTO(List<Message> messages);

}
