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

    public void update(
            String text,
            Integer rate
    ) {
        this.text = text;
        this.rate = rate;

        registerEvent(new ReviewUpdatedEvent(id, reviewedSpace.getId()));
    }

    public static Review create(
            Client reviewer,
            Space reviewedSpace,
            String text,
            Integer rate
    ) {
        Review review = new Review();
        review.setReviewer(reviewer);
        review.setReviewedSpace(reviewedSpace);
        review.setText(text);
        review.setRate(rate);

        review.registerEvent(
                new ReviewCreatedEvent(
                        reviewer.getId(),
                        reviewedSpace.getId()
                )
        );

        return review;
    }
}
