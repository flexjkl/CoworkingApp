package dev.vorstu.coworkingapp.entities.places;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CoworkingPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Space space;

    private String title;

    private String description;

    private boolean isFree;
}
