package strategy.sort;

import entites.Task;

import java.util.Comparator;
import java.util.List;

public class SortByPriority implements SortStrategy {
    @Override
    public List<Task> sort(List<Task> tasks) {
        tasks.sort(Comparator.comparing(Task::getPriority));
        return tasks;
    }

}
