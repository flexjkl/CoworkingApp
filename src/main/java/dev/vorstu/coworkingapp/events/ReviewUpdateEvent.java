package dev.vorstu.coworkingapp.events;

import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class ReviewUpdateEvent extends ApplicationEvent {

    private final Long reviewedSpaceId;

    public ReviewUpdateEvent(Object source,
                               Long reviewedSpaceId
    ) {
        super(source);
        this.reviewedSpaceId = reviewedSpaceId;
    }

}
