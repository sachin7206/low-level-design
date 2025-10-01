package state.TaskState;

import entites.Task;
import enums.TaskStatus;

public class CompletedState implements TaskState {

    @Override
    public void startProgress(Task task) {
        System.out.println("Task is already completed and cannot be started again.");
    }

    @Override
    public void completeTask(Task task) {
        System.out.println("Task is already completed.");
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.COMPLETED;
    }
}
