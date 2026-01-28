package dev.vorstu.coworkingapp.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

import java.time.LocalDateTime;

@Getter
public class ReviewCreationEvent extends ApplicationEvent {

    private final Long reviewedSpaceId;

    public ReviewCreationEvent(Object source,
                               Long reviewedSpaceId
    ) {
        super(source);
        this.reviewedSpaceId = reviewedSpaceId;
    }

}
