package lv.initex.todo.database;

import lv.initex.todo.domain.TaskStatusEnum;
import lv.initex.todo.domain.User;
import lv.initex.todo.domain.UserTask;

import java.util.List;
import java.util.Optional;

public interface UserTaskRepository {
    void addUser(User user);

    void setActiveUser(User user);

    Optional<User> findUserByName(String userName);

    User getActiveUser();

    void addTask(UserTask userTask);

    void modifyTaskName(UserTask task , String newName);

    void modifyTaskStatus(UserTask task, TaskStatusEnum status);

    boolean deleteTask(UserTask userTask);

    Optional<UserTask> findTaskByTitle(String taskName);

    List<User> getAllUsers();

    List<UserTask> getAllUserTasks(User user);

}
