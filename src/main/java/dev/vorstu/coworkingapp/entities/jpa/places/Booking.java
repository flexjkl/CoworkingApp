package dev.vorstu.coworkingapp.entities.jpa.places;

import dev.vorstu.coworkingapp.entities.jpa.communication.Chat;
import dev.vorstu.coworkingapp.entities.jpa.users.Client;
import dev.vorstu.coworkingapp.entities.jpa.utils.PricePlan;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "place_id")
    private CoworkingPlace place;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "price_plan_id", nullable = false)
    private PricePlan pricePlan;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false)
    private Chat chat;

    private String additions;

    private Instant startTime;

    private Instant endTime;
}
