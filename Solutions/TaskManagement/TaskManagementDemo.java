import entites.Task;
import entites.User;
import enums.Priority;
import state.TaskState.OpenState;
import strategy.sort.SortByUser;
import strategy.sort.SortStrategy;

import java.time.OffsetDateTime;
import java.util.Set;

public class TaskManagementDemo {
    public static void main(String[] args) {
        TaskManagementController taskManagementController = TaskManagementController.getInstance();

        // step 1 - creating users...
        User sachinUser = taskManagementController.createUser("sachin");
        User sowmyaUser = taskManagementController.createUser("sowmya");
        User rithikUser = taskManagementController.createUser("rithik");
        User shivayaUser = taskManagementController.createUser("shivaya");
        // step 2 - updating user...
        taskManagementController.updateUser(sachinUser, "sachin tendulkar");

        // step 3 - Creating tasks...

        Task task1 = taskManagementController.createTask("user task 1", "Finalize and submit the project report by end of the week.",
                OffsetDateTime.now().plusDays(5), Priority.HIGH, new OpenState(), Set.of(sachinUser, sowmyaUser));
        Task task2 = taskManagementController.createTask("user task 2", "Prepare a presentation for the upcoming client meeting.",
                OffsetDateTime.now().plusDays(3), Priority.MEDIUM, new OpenState(), Set.of(sowmyaUser, rithikUser));
        Task task3 = taskManagementController.createTask("user task 3", "Organize a team-building activity for the department.",
                OffsetDateTime.now().plusDays(10), Priority.LOW, new OpenState(), Set.of(rithikUser, shivayaUser));
        Task task4 = taskManagementController.createTask("user task 4", "Conduct a training session on the new software tools.",
                OffsetDateTime.now().plusDays(7), Priority.URGENT, new OpenState(), Set.of(shivayaUser, sachinUser));

        // step 4 - claim task by user
        taskManagementController.claimTask(task1, sachinUser);
        taskManagementController.claimTask(task2, sowmyaUser);
        taskManagementController.claimTask(task3, rithikUser);
        taskManagementController.claimTask(task4, shivayaUser);

        // step 5 - unClaiming tasks to users.
//        taskManagementController.unClaimTask(task1);
//        taskManagementController.unClaimTask(task2);
//        taskManagementController.unClaimTask(task3);
//        taskManagementController.unClaimTask(task4);

        // step 6 - start task.
        taskManagementController.startTask(task1);
        taskManagementController.startTask(task2);
        taskManagementController.startTask(task3);
        taskManagementController.startTask(task4);

        // step 7 - complete task.
        taskManagementController.completeTask(task1);
        taskManagementController.completeTask(task2);

        // step 8 - search Task
        SortStrategy sortStrategy = new SortByUser();
        taskManagementController.searchTask("user task", sortStrategy);

    }
}
