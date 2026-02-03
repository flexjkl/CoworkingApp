package dev.vorstu.coworkingapp.events.listeners;

import dev.vorstu.coworkingapp.events.SignInEvent;
import dev.vorstu.coworkingapp.events.SignUpEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class AuthEventListener {

    @EventListener
    public void signInEventHandler(SignInEvent signInEvent) {
        System.out.println("SIGN IN: " + signInEvent.getUsername() + " " + signInEvent.getTime());
    }

    @EventListener
    public void signUpEventHandler(SignUpEvent signUpEvent) {
        System.out.println("SIGN UP: " + signUpEvent.getUsername() + " " + signUpEvent.getTime());
    }
}
