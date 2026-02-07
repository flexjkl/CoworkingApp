package dev.vorstu.coworkingapp.events;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;

@RequiredArgsConstructor
@Getter
public final class ReviewUpdatedEvent {
    private final Long reviewId;
    private final Long reviewedSpaceId;
    private final LocalDateTime occurredAt = LocalDateTime.now();
}
