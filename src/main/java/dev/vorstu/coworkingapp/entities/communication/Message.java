package dev.vorstu.coworkingapp.entities.communication;

import dev.vorstu.coworkingapp.entities.users.Person;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id")
    private Person sender;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    private Chat chat;

    private String text;

    @CreatedDate
    private Instant sendTime;

    private boolean isRead;

    private boolean isChanged = false;

}
