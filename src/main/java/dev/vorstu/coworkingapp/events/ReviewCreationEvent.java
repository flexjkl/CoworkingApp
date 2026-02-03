package dev.vorstu.coworkingapp.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

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
