package lv.initex.todo.domain;

import lv.initex.todo.domain.Task;
import lv.initex.todo.domain.User;

public class UserTask {
    private User user;
    private Task task;

    public UserTask(User user, Task task){
        this.user=user;
        this.task=task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
