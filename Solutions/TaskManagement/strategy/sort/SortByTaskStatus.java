package strategy.sort;

import entites.Task;

import java.util.Comparator;
import java.util.List;

public class SortByTaskStatus implements SortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        tasks.sort(Comparator.comparing(task -> task.getState().getStatus()));
        return tasks;
    }
}
