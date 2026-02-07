package dev.vorstu.coworkingapp.entities.jpa.communication;

import dev.vorstu.coworkingapp.entities.jpa.places.Space;
import dev.vorstu.coworkingapp.entities.jpa.users.Client;
import dev.vorstu.coworkingapp.events.ReviewCreatedEvent;
import dev.vorstu.coworkingapp.events.ReviewUpdatedEvent;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.time.Instant;

@Entity
@Data
public class Review extends AbstractAggregateRoot<Review> {

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

    public void updateReview(Long changedByUserId) {
        registerEvent(new ReviewUpdatedEvent(
                id,
                reviewedSpace.getId()
        ));
    }

    public void createReview(Long createByUserId) {
        registerEvent(new ReviewCreatedEvent(
                createByUserId,
                reviewedSpace.getId()
        ));
    }
}
