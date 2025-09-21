package entities;

import enums.EventType;

public class Event {
    private final EventType eventType;
    private final User voter;
    private final Post targetPost;
    public Event(EventType eventType, User voter, Post targetPost) {
        this.eventType = eventType;
        this.voter = voter;
        this.targetPost = targetPost;
    }

    public EventType getEventType() {
        return eventType;
    }

    public User getVoter() {
        return voter;
    }

    public Post getTargetPost() {
        return targetPost;
    }


}
