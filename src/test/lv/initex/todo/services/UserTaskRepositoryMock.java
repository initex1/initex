package lv.initex.todo.services;

import lv.initex.todo.database.jdbc.UserTaskRepositoryImpl;
import lv.initex.todo.domain.Task;
import lv.initex.todo.domain.User;
import lv.initex.todo.domain.UserTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserTaskRepositoryMock extends UserTaskRepositoryImpl {

    private boolean isUserAdded = false;
    private boolean isTaskAdded = false;
    private boolean isTaskDeleted = false;
    private Optional<UserTask> isTaskFound;
    private Optional<User> isUserFound;
    private boolean isUsersPrinted=false;
    private boolean isTasksPrinted =false;

    @Override
    public void addUser(User user) {
        isUserAdded = true;
    }

    @Override
    public void addTask(UserTask userTask) {
        isTaskAdded = true;
    }

    public boolean isUserAdded() {
        return isUserAdded;
    }

    public boolean isTaskAdded() {
        return isTaskAdded;
    }

    @Override
    public boolean deleteTask(UserTask userTask) {
        isTaskDeleted = true;
        return isTaskDeleted;
    }

    @Override
    public Optional<UserTask> findTaskByTitle(String taskName) {
        User user = new User();
        Task task = new Task();
        isTaskFound = Optional.ofNullable(new UserTask(user, task));
        return isTaskFound;
    }

    public boolean isTaskDeleted() {
        return isTaskDeleted;
    }

    @Override
    public Optional<User> findUserByName(String taskName) {
        User user = new User();
        user.setUserName("user");
        isUserFound = Optional.ofNullable(user);
        return isUserFound;
    }

    @Override
    public List<UserTask> getAllUserTasks(User user) {
        List<UserTask> userTask=new ArrayList<>();
        isTasksPrinted =true;
        return userTask;
    }

    public boolean isTasksPrinted() {
        return isTasksPrinted;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users=new ArrayList<>();
        isUsersPrinted =true;
        return users;
    }

    public boolean isUsersPrinted() {
        return isUsersPrinted;
    }
}
