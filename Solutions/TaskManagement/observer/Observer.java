package observer;

import entites.Task;

public interface Observer {
    void notifyUser(Task task, String message);
}
