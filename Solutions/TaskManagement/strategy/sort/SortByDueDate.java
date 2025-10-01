package strategy.sort;

import entites.Task;

import java.util.Comparator;
import java.util.List;

public class SortByDueDate implements SortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getDueDate));
        return tasks;
    }
}
