package observer;

import entites.Task;
import entites.User;

public class TaskObserver implements Observer{
    @Override
    public void notifyUser(Task task, String message) {
        if(task.getAssignedUser() != null) {
            System.out.println("Notification to " + task.getAssignedUser().getUserName() + " for TaskTitle -  " + task.getTitle()  + " : "+ message + ". where you are assignee of the task.");
        } else {
            // send mail to all candidate users
            for (User u : task.getCandidateUsers()) {
                System.out.println("Notification to " + u.getUserName() + " for TaskTitle -  " + task.getTitle()  + " : "+ message + ". where you are a candidate user for the task.");
            }
        }
    }
}
