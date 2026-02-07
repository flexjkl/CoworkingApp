package dev.vorstu.coworkingapp.events.listeners;

import dev.vorstu.coworkingapp.events.ReviewCreatedEvent;
import dev.vorstu.coworkingapp.events.ReviewUpdatedEvent;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Component
@RequiredArgsConstructor
public class ReviewEventListener {

    private final SpaceRepository spaceRepository;
    private final EntityManager entityManager;

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void reviewCreationEventHandler(ReviewCreatedEvent reviewCreatedEvent) {

        log.info("User ID " +
                 reviewCreatedEvent.getCreateByUserId() +
                 "posted a review of the space ID " +
                 reviewCreatedEvent.getReviewedSpaceId() +
                 " at " +
                 reviewCreatedEvent.getOccurredAt()
        );

        entityManager.flush();

        spaceRepository.recalculateSpaceRatingById(reviewCreatedEvent.getReviewedSpaceId());

    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void reviewUpdateEventHandler(ReviewUpdatedEvent reviewUpdatedEvent) {

        log.info("Review with ID " +
                reviewUpdatedEvent.getReviewId() +
                "on Space with ID " +
                reviewUpdatedEvent.getReviewedSpaceId() +
                " at " +
                reviewUpdatedEvent.getOccurredAt()
        );

        entityManager.flush();

        spaceRepository.recalculateSpaceRatingById(reviewUpdatedEvent.getReviewedSpaceId());

    }

}
