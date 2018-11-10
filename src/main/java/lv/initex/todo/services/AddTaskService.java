package lv.initex.todo.services;

import lv.initex.todo.database.UserTaskRepository;
import lv.initex.todo.domain.Task;
import lv.initex.todo.domain.TaskStatusEnum;
import lv.initex.todo.domain.User;
import lv.initex.todo.domain.UserTask;

public class AddTaskService {

    private UserTaskRepository database;

    public AddTaskService(UserTaskRepository database) {
        this.database = database;
    }

    public void addTask(String taskName) {
        User user = database.getActiveUser();
        Task task = new Task();
        task.setTaskName(taskName);
        task.setStatus(TaskStatusEnum.ACTIVE);
        UserTask userTask= new UserTask(user, task);
        database.addTask(userTask);
    }
}
