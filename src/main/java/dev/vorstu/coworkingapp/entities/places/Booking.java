package dev.vorstu.coworkingapp.entities.places;

import dev.vorstu.coworkingapp.entities.users.Client;
import dev.vorstu.coworkingapp.entities.utils.PricePlan;
import jakarta.persistence.*;

@Entity
public class Booking {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Client client;

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private CoworkingPlace place;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private PricePlan pricePlan;

    private String additions;
}
