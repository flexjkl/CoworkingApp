package dev.vorstu.coworkingapp.entities.places;

import dev.vorstu.coworkingapp.entities.communication.Comment;
import dev.vorstu.coworkingapp.entities.communication.Review;
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id")
    private Owner owner;

    private String title;

    private BigDecimal rating;

    @OneToMany(mappedBy = "space", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CoworkingPlace> places = new ArrayList<>();

    @OneToMany(mappedBy = "reviewedSpace", cascade = CascadeType.ALL)
    private List<Review> reviews = new ArrayList<>();

    @OneToMany(mappedBy = "commentedSpace", cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();
}
