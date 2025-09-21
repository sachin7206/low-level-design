package entities;

import enums.EventType;
import enums.VoteType;
import observer.PostObserver;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Post extends Content {
    private final Map<String, VoteType> voters = new ConcurrentHashMap<>();
    private final List<PostObserver> observers = new ArrayList<>();
    public Post(String body, User author) {
        super(body, author);
    }

    public void addObserver(PostObserver observer) {
        observers.add(observer);
    }

    public void notifyObservers(Event event) {
        observers.forEach(o -> o.onPostEvent(event));
    }

    public synchronized void vote(User user, VoteType voteType) {
        if (this.getAuthor().getUserId().equals(user.getUserId())) {
            System.out.println("Users cannot vote on their own posts.");
            return;
        }

        if (voters.containsKey(user.getUserId()) && voters.get(user.getUserId()) == voteType) {
            System.out.println("User has already " + voteType + "d this post.");
            return;
        }

        if (voters.containsKey(user.getUserId()) && voters.get(user.getUserId()) != voteType) {
            voters.put(user.getUserId(), voteType);
        }

        voters.put(user.getUserId(), voteType);
        EventType eventType = EventType.UPVOTE_QUESTION;
        if (this instanceof Question) {
            eventType = voteType == VoteType.UPVOTE ? EventType.UPVOTE_QUESTION : EventType.DOWNVOTE_QUESTION;
        } else {
            eventType = voteType == VoteType.UPVOTE ? EventType.UPVOTE_ANSWER : EventType.DOWNVOTE_ANSWER;
        }

        notifyObservers(new Event(eventType, user, this));
    }
}
