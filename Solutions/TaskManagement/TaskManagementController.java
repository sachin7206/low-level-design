import entites.Task;
import entites.User;
import enums.TaskStatus;
import observer.Observer;
import state.TaskState.TaskState;
import strategy.sort.SortStrategy;

import java.io.ObjectInputValidation;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class TaskManagementController {

    private static TaskManagementController taskManagementInstance;
    private Map<String, Set<User> > taskCandidateUsers;
    private Map<String, User > assignedUser;
    private Map<String, Task> tasks;
    private Map<String, User> users;
    private TaskManagementController() {
        // Private constructor to prevent instantiation
        taskCandidateUsers = new ConcurrentHashMap<>();
        assignedUser = new ConcurrentHashMap<>();
        tasks = new ConcurrentHashMap<>();
        users = new ConcurrentHashMap<>();
    }

    public static synchronized TaskManagementController getInstance() {
        if (taskManagementInstance == null) {
            taskManagementInstance = new TaskManagementController();
        }
        return taskManagementInstance;
    }

    public User createUser(String name) {
        User user = new User(name);
        users.put(user.getUserId(), user);
        return user;
    }

    public Task createTask(String title, String description,
                           java.time.OffsetDateTime dueDate,
                           enums.Priority priority,
                           TaskState state,
                           Set<User> candidateUsers) {
        Task createdTask = new Task.TaskBuilder()
                .withTitle(title)
                .withDescription(description)
                .withDueDate(dueDate)
                .withPriority(priority)
                .withState(state)
                .withCandidateUsers(candidateUsers)
                .build();
        taskCandidateUsers.put(createdTask.getId(), createdTask.getCandidateUsers());
        tasks.put(createdTask.getId(), createdTask);
        return createdTask;
    }



    public void updateUser(User user, String newName) {
       user.setUserName(newName);
    }

    public void claimTask(Task task, User user) {
        Set<User> candidateUsers = taskCandidateUsers.get(task.getId());
        if(task.getAssignedUser() != null) {
            throw new IllegalArgumentException("Task is already claimed by another user" + task.getAssignedUser().getUserName());
        }

        if (!candidateUsers.contains(user)) {
            throw new IllegalArgumentException("User is not a candidate for this task");
        }

        taskCandidateUsers.remove(task.getId());
        task.setAssignedUsers(user);
        assignedUser.put(task.getId(), task.getAssignedUser());
    }

    public void unClaimTask(Task task) {
        if(task.getAssignedUser() == null) {
            throw new IllegalArgumentException("Task is not claimed by any user");
        }
        task.setAssignedUsers(null);
        taskCandidateUsers.put(task.getId(), task.getCandidateUsers());
    }

    public void startTask(Task task) {
        task.startProgress();
    }

    public void completeTask(Task task) {
        task.completeTask();
    }

    public List<Task> listTasksByUser(String userId) {
        User user = users.get(userId);
        return tasks.values().stream()
                .filter(task -> user.equals(task.getAssignedUser()))
                .toList();
    }

    public List<Task> listTasksByStatus(TaskStatus status) {
        return tasks.values().stream()
                .filter(task -> task.getState().getStatus() == status)
                .collect(Collectors.toList());
    }

    public void searchTask(String searchString, SortStrategy sortStrategy) {
        List<Task> result = new ArrayList<>();
        for (Task task : tasks.values()) {
            if (task.getTitle().contains(searchString) || task.getDescription().contains(searchString)) {
                result.add(task);
            }
        }
        sortStrategy.sort(result);
        for (Task task : result) {
            System.out.println("Task Title: " + task.getTitle() + ", Assigned User: " +
                    (task.getAssignedUser() != null ? task.getAssignedUser().getUserName() : "No assignee") + ", Description: " + task.getDescription() + ", Status: " + task.getState().getStatus() + ", Priority: " + task.getPriority() + ", Due Date: " + task.getDueDate());
        }
    }


}
