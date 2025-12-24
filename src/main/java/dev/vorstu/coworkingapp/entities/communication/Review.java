package dev.vorstu.coworkingapp.entities.communication;

import dev.vorstu.coworkingapp.entities.places.Space;
import dev.vorstu.coworkingapp.entities.users.Client;
import jakarta.persistence.*;
import lombok.Data;

import java.time.Instant;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewer_id")
    private Client reviewer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reviewed_space_id")
    private Space reviewedSpace;

    private String text;

    private Integer rate;

    private Instant createdAt;
}
