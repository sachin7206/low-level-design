package entites;

import observer.Observer;

import java.util.UUID;

public class User {
    private final String userId;
    private String name;
    public User(String name) {
        this.userId = UUID.randomUUID().toString();
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public String getUserName() {
        return name;
    }

    public void setUserName(String name) {
        this.name = name;
    }
}
