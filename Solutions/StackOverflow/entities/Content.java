package entities;

import java.time.LocalDateTime;

public abstract class Content {
    private final String id;
    private String body;
    private final User author;
    private final LocalDateTime creationTime;

    public Content(String body, User author) {
        this.id = java.util.UUID.randomUUID().toString();
        this.body = body;
        this.author = author;
        this.creationTime = LocalDateTime.now();
    }

    public String getBody() {
        return body;
    }

    public User getAuthor() {
        return author;
    }

    public String getId() {
        return id;
    }
}
