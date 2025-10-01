package entites;

import enums.Priority;
import observer.Observer;
import observer.TaskObserver;
import state.TaskState.TaskState;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.UUID;

public class Task {
    private final String taskId;
    private String title;
    private String description;
    private OffsetDateTime dueDate;
    private Priority priority;
    private TaskState state;
    private User assignedUsers;
    private Set<User> candidateUsers;
    private List<Observer> observers;

    public Task(TaskBuilder builder) {
        observers = new ArrayList<>();
        addObservers(new TaskObserver());
        this.taskId = builder.taskId;
        this.candidateUsers = builder.candidateUsers;
        this.title = builder.title;
        this.description = builder.description;
        this.dueDate = builder.dueDate;
        this.priority = builder.priority;
        this.state = builder.state;
        assignedUsers = null;
        notifyObservers("Task Created with state: " + state);
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public OffsetDateTime getDueDate() {
        return dueDate;
    }

    public Priority getPriority() {
        return priority;
    }

    public TaskState getState() {
        return state;
    }

    public void setState(TaskState state) {
        this.state = state;
        notifyObservers("Task status is changed to: " + state);
    }


    public void addObservers(Observer observer) {
        this.observers.add(observer);
    }

    public void notifyObservers(String message) {
        for (Observer observer : observers) {
            observer.notifyUser(this, message);
        }
    }

    public void setAssignedUsers(User assignedUsers) {
        this.assignedUsers = assignedUsers;
        notifyObservers("You have been assigned to the task.");
    }

    public User getAssignedUser() {
        return assignedUsers;
    }

    public Set<User> getCandidateUsers() {
        return candidateUsers;
    }

    public String getId() {
        return taskId;
    }

    public void startProgress() { state.startProgress(this); }
    public void completeTask() { state.completeTask(this); }

    // --- Builder Pattern ---
    public static class TaskBuilder {
        private final String taskId;
        private String title;
        private String description;
        private OffsetDateTime dueDate;
        private Priority priority;
        private TaskState state;
        private User assignedUsers;
        private Set<User> candidateUsers;
        private List<Observer> observers;
        public TaskBuilder() {
            this.taskId = UUID.randomUUID().toString();
        }

        public TaskBuilder withTitle(String title) { this.title = title; return this; }
        public TaskBuilder withDescription(String description) { this.description = description; return this; }
        public TaskBuilder withDueDate(OffsetDateTime dueDate) { this.dueDate = dueDate; return this; }
        public TaskBuilder withPriority(Priority priority) { this.priority = priority; return this; }
        public TaskBuilder withState(TaskState state) { this.state = state; return this; }
        public TaskBuilder withCandidateUsers(Set<User> candidateUsers) { this.candidateUsers = candidateUsers; return this; }
        public Task build() {
            return new Task(this);
        }
    }
}
