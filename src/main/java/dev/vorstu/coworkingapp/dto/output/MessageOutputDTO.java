package dev.vorstu.coworkingapp.dto.output;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Getter
@Setter
public class MessageOutputDTO {

    private Long id;

    private String senderUsername;

    private Long chatId;

    private String text;

    private Instant sendTime;

    private boolean isRead;

    private boolean isChanged;

}
