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

    //todo ???
    private boolean isFree = true;

    @OneToOne(mappedBy = "place", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Booking booking;

    public void release() {
        isFree = true;
        booking = null;
    }

    public void book(Booking booking) {
        isFree = false;
        this.booking = booking;
    }
}
