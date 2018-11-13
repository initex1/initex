package lv.initex.todo.services;

import lv.initex.todo.database.UserTaskRepository;
import lv.initex.todo.domain.TaskStatusEnum;
import lv.initex.todo.domain.User;
import lv.initex.todo.domain.UserTask;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ModifyTaskService {

    private UserTaskRepository database;

    public ModifyTaskService(UserTaskRepository database) {
        this.database = database;
    }

    public UserTask getTaskForModify(String modifyTaskName) {
        Optional<UserTask> foundTask = database.findTaskByTitle(modifyTaskName);
        if (foundTask.isPresent()) {
            UserTask userTask = foundTask.get();
            return userTask;
        }
        return null;
    }

    public void modifyTaskName(UserTask taskForModify, String newName) {
        database.modifyTaskName(taskForModify, newName);
    }

    public void modifyTaskStatus(UserTask taskForModify, int newStatus) {
        switch (newStatus){
            case 1:
                database.modifyTaskStatus(taskForModify, TaskStatusEnum.ACTIVE);
                break;
            case 2:
                database.modifyTaskStatus(taskForModify, TaskStatusEnum.INPROGRESS);
                break;
            case 3:
                database.modifyTaskStatus(taskForModify, TaskStatusEnum.CANCELLED);
                break;
            case 4:
                database.modifyTaskStatus(taskForModify, TaskStatusEnum.FINISHED);
                break;
        }
    }

    public List<UserTask> getAllTasks() {
        User user = database.getActiveUser();
        return database.getAllUserTasks(user);
    }
}
