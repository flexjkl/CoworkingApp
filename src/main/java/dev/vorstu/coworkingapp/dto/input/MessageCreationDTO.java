package dev.vorstu.coworkingapp.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MessageCreationDTO {

    private Long personId;

    private Long chatId;

    private String text;

}
