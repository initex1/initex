package lv.initex.todo.services;

import lv.initex.todo.database.UserTaskRepository;
import lv.initex.todo.domain.UserTask;

import java.util.Optional;

public class DeleteTaskService {
    private UserTaskRepository database;

    public DeleteTaskService(UserTaskRepository database) {
        this.database = database;
    }

    public boolean deleteTask(String taskToDelete) {
        Optional<UserTask> foundTask = database.findTaskByTitle(taskToDelete);
        if (foundTask.isPresent()) {
            UserTask userTask = foundTask.get();
            return database.deleteTask(userTask);
        } else {
            return false;
        }
    }
}
