package dev.vorstu.coworkingapp.events.listeners;

import dev.vorstu.coworkingapp.events.ReviewCreationEvent;
import dev.vorstu.coworkingapp.events.ReviewUpdateEvent;
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
    public void reviewCreationEventHandler(ReviewCreationEvent reviewCreationEvent) {

        spaceRepository.recalculateSpaceRatingById(reviewCreationEvent.getReviewedSpaceId());

    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void reviewUpdateEventHandler(ReviewUpdateEvent reviewUpdateEvent) {

        spaceRepository.recalculateSpaceRatingById(reviewUpdateEvent.getReviewedSpaceId());

    }

}
