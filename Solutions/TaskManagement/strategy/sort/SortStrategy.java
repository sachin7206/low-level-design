package strategy.sort;

import entites.Task;

import java.util.List;

public interface SortStrategy {
    List<Task> sort(List<Task> tasks);
}
