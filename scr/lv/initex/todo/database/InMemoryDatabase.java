package lv.initex.todo.database;

import lv.initex.todo.domain.TaskStatusEnum;
import lv.initex.todo.domain.User;
import lv.initex.todo.domain.UserTask;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class InMemoryDatabase implements UserTaskRepository {

    private List<UserTask> userTasksList = new ArrayList<UserTask>();
    private List<User> users = new ArrayList<User>();
    private User activeUser = new User();

    @Override
    public void addUser(User user) {
        users.add(user);
    }

    @Override
    public void setActiveUser(User user) {
        activeUser = user;
    }

    @Override
    public Optional<User> findUserByName(String userName) {
        return users.stream()
                .filter(p -> p.getUserName().equals(userName))
                .findFirst();
    }

    @Override
    public User getActiveUser() {
        return activeUser;
    }

    @Override
    public void addTask(UserTask userTask) {
        userTasksList.add(userTask);
    }

    @Override
    public void modifyTaskName(UserTask task, String newName) {
        userTasksList.get(userTasksList.indexOf(task)).getTask().setTaskName(newName);
    }

    @Override
    public void modifyTaskStatus(UserTask task, TaskStatusEnum status) {
userTasksList.get(userTasksList.indexOf(task)).getTask().setStatus(status);
    }

    @Override
    public boolean deleteTask(UserTask userTask) {
        return false;
    }

    @Override
    public Optional<UserTask> findTaskByTitle(String taskName) {
        return userTasksList.stream()
                .filter(p -> p.getTask().getTaskName().equals(taskName))
                .findFirst();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<User>(users);
    }

    @Override
    public List<UserTask> getAllUserTasks(User user) {
        List<UserTask> userTasks = new ArrayList<>();
        for (UserTask task : userTasksList) {
            if (task.getUser().equals( user)) {
                userTasks.add(task);
            }
        }
        return userTasks;
    }
}
