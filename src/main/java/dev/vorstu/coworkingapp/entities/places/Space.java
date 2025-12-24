package dev.vorstu.coworkingapp.entities.places;

import dev.vorstu.coworkingapp.entities.users.Owner;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
public class Space {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Owner owner;

    private String title;

    private BigDecimal rating;

    @OneToMany(mappedBy = "space")
    private List<CoworkingPlace> places = new ArrayList<>();
}
