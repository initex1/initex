package lv.initex.todo.services;

import lv.initex.todo.database.UserTaskRepository;
import lv.initex.todo.domain.User;
import lv.initex.todo.domain.UserTask;

import java.util.List;

public class PrintTaskListService {
    private UserTaskRepository database;

    public PrintTaskListService(UserTaskRepository database){
        this.database=database;
    }

    public List<UserTask> getAllTasks(){
        User user=database.getActiveUser();
        return database.getAllUserTasks(user);
    }
}
