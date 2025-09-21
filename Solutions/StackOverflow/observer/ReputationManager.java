package observer;

import entities.Event;
import enums.EventType;

public class ReputationManager implements PostObserver {
    private static final int upvoteQuestion = 1;
    private static final int downvoteQuestion = -1;
    private static final int upvoteAnswer = 1;
    private static final int downvoteAnswer = -1;
    private static final int acceptAnswer = 5;

    @Override
    public void onPostEvent(Event event) {
        // Logic to update user reputation based on the event
        switch (event.getEventType()) {
            case EventType.UPVOTE_QUESTION:
                event.getTargetPost().getAuthor().incrementReputation(upvoteQuestion);
                break;
            case EventType.DOWNVOTE_QUESTION:
                event.getTargetPost().getAuthor().incrementReputation(downvoteQuestion);
                break;
            case EventType.UPVOTE_ANSWER:
                event.getTargetPost().getAuthor().incrementReputation(upvoteAnswer);
                break;
            case EventType.DOWNVOTE_ANSWER:
                event.getTargetPost().getAuthor().incrementReputation(downvoteAnswer);
                break;
            case EventType.ACCEPT_ANSWER:
                event.getTargetPost().getAuthor().incrementReputation(acceptAnswer);
                break;
            default:
                // No action for other events
                break;
        }
    }
}
