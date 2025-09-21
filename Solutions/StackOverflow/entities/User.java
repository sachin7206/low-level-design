package entities;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public class User {
    private String name;
    private String userName;
    private String gmail;
    private String userId;
    private AtomicInteger reputation;
    public User(String name, String userName, String gmail) {
        this.name = name;
        this.userName = userName;
        this.gmail = gmail;
        this.userId = UUID.randomUUID().toString();
        this.reputation = new AtomicInteger(0);
        System.out.println(userName + " is created Successfully.");
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void incrementReputation(int points) {
        this.reputation.addAndGet(points);
    }

    public int getReputation() {
        return reputation.get();
    }
}
