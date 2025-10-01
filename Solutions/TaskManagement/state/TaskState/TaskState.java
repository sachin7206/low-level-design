package state.TaskState;

import entites.Task;
import enums.TaskStatus;

public interface TaskState {
    void startProgress(Task task);
    void completeTask(Task task);
    TaskStatus getStatus();
}
