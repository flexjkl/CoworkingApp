package dev.vorstu.coworkingapp.events.listeners;

import dev.vorstu.coworkingapp.events.ReviewCreatedEvent;
import dev.vorstu.coworkingapp.events.ReviewUpdatedEvent;
import dev.vorstu.coworkingapp.repositories.SpaceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class ReviewEventListener {

    private final SpaceRepository spaceRepository;

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void reviewCreationEventHandler(ReviewCreatedEvent reviewCreatedEvent) {

        spaceRepository.recalculateSpaceRatingById(reviewCreatedEvent.getReviewedSpaceId());

    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void reviewUpdateEventHandler(ReviewUpdatedEvent reviewUpdatedEvent) {

        spaceRepository.recalculateSpaceRatingById(reviewUpdatedEvent.getReviewedSpaceId());

    }

}
