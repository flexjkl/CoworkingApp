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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private Owner owner;

    @OneToOne(mappedBy = "chat", fetch = FetchType.LAZY)
    private Booking booking;

    @OneToMany(mappedBy = "chat", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Message> messages = new LinkedList<>();

}
