package strategy.sort;

import entites.Task;
import entites.User;

import java.util.Comparator;
import java.util.List;
import java.util.Set;

public class SortByUser implements SortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        tasks.sort(Comparator.comparing(task -> {
            if (task.getAssignedUser() == null) {
                // Get candidate users set
                Set<User> candidates = task.getCandidateUsers();
                if (candidates == null || candidates.isEmpty()) {
                    return ""; // or some default value for empty candidate set
                }
                // Find candidate with smallest username alphabetically
                return candidates.stream()
                        .map(User::getUserName)
                        .min(String::compareTo)
                        .orElse("");
            } else {
                return task.getAssignedUser().getUserName();
            }
        }));
        return tasks;
    }
}
