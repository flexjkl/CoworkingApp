package dev.vorstu.coworkingapp.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class SignInEvent extends ApplicationEvent {

    private final String username;
    private final LocalDateTime time;

    public SignInEvent(Object source, String username, LocalDateTime time) {
        super(source);
        this.username = username;
        this.time = time;
    }

}
