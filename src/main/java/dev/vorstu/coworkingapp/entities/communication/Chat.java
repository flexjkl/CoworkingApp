package dev.vorstu.coworkingapp.entities.communication;

import dev.vorstu.coworkingapp.entities.places.Booking;
import dev.vorstu.coworkingapp.entities.users.Client;
import dev.vorstu.coworkingapp.entities.users.Owner;
import jakarta.persistence.*;
import lombok.Data;

import java.util.LinkedList;
import java.util.List;

@Entity
@Data
public class Chat {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Owner owner;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Booking booking;

    @OneToMany(mappedBy = "chat")
    private List<Message> messages = new LinkedList<>();

}
