package dev.vorstu.coworkingapp.entities.communication;

import dev.vorstu.coworkingapp.entities.places.Space;
import dev.vorstu.coworkingapp.entities.users.Client;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import java.time.Instant;

@Entity
@Data
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reviewer_id", nullable = false)
    private Client reviewer;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "reviewed_space_id", nullable = false)
    private Space reviewedSpace;

    private String text;

    private Integer rate;

    @CreatedDate
    private Instant createdAt;
}
