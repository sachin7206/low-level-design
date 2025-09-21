package entities;

import java.util.UUID;

public class Tag {
    private final String id;
    String tagName;
    public Tag(String tagName) {
        this.id = UUID.randomUUID().toString();
        this.tagName = tagName;
    }

    public String getTagId() {
        return id;
    }

    public String getTagName() {
        return tagName;
    }
}
