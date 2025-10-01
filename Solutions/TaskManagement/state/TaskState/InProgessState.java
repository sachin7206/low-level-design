package state.TaskState;

import entites.Task;
import enums.TaskStatus;

public class InProgessState implements TaskState {

    @Override
    public void startProgress(Task task) {
        System.out.println("Task is already in progress.");
    }

    @Override
    public void completeTask(Task task) {
        task.setState(new CompletedState());
        System.out.println("Task marked as completed.");
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.IN_PROGRESS;
    }
}
