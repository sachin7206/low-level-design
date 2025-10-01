package state.TaskState;

import entites.Task;
import enums.TaskStatus;

public class OpenState implements TaskState {

    @Override
    public void startProgress(Task task) {
        task.setState(new InProgessState());
        System.out.println("Task is now In Progress.");
    }

    @Override
    public void completeTask(Task task) {
        System.out.println("Task must be in progress before it can be completed.");
    }

    @Override
    public TaskStatus getStatus() {
        return TaskStatus.OPEN;
    }
}
