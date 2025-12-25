package dev.vorstu.coworkingapp.dto.output;

import java.time.Instant;

public class MessageOutputDTO {

    private Long id;

    private String senderUsername;

    private Long chatId;

    private String text;

    private Instant sendTime;

    private boolean isRead;

    private boolean isChanged;

}
