package dev.vorstu.coworkingapp.entities.users;

import dev.vorstu.coworkingapp.entities.places.Booking;
import dev.vorstu.coworkingapp.entities.communication.Chat;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Client extends Person{

    @OneToMany(mappedBy = "client")
    private List<Booking> bookings = new ArrayList<>();

    @OneToMany(mappedBy = "client")
    private List<Chat> chats = new ArrayList<>();
}
