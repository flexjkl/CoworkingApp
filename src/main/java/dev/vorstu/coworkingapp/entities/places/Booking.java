package dev.vorstu.coworkingapp.entities.places;

import dev.vorstu.coworkingapp.entities.communication.Chat;
import dev.vorstu.coworkingapp.entities.users.Client;
import dev.vorstu.coworkingapp.entities.utils.PricePlan;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    @OneToOne(fetch = FetchType.LAZY)
    private CoworkingPlace place;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "price_plan_id")
    private PricePlan pricePlan;

    @OneToOne(fetch = FetchType.LAZY, orphanRemoval = true)
    private Chat chat;

    private String additions;
}
