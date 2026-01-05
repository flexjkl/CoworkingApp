package dev.vorstu.coworkingapp.entities.places;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CoworkingPlace {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "space_id", nullable = false)
    private Space space;

    private String title;

    private String description;

    private boolean isFree;
}
