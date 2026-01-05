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

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "sender_id", nullable = false)
    private Person sender;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "chat_id", nullable = false)
    private Chat chat;

    private String text;

    @CreatedDate
    private Instant sendTime;

    private boolean isRead;

    private boolean isChanged = false;

}
