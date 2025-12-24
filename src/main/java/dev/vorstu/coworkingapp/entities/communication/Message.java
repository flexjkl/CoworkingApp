package dev.vorstu.coworkingapp.entities.communication;

import dev.vorstu.coworkingapp.entities.users.Person;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Person sender;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Chat chat;

    private String text;

    private Instant sendTime;

    private boolean isRead;

    private boolean isChanged;

}
