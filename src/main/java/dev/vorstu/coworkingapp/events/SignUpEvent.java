package dev.vorstu.coworkingapp.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class SignUpEvent extends ApplicationEvent {

    private final String username;
    private final LocalDateTime time;

    public SignUpEvent(Object source, String username, LocalDateTime time) {
        super(source);
        this.username = username;
        this.time = time;
    }

}
