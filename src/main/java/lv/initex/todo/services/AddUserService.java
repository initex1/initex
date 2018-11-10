package lv.initex.todo.services;

import lv.initex.todo.database.UserTaskRepository;
import lv.initex.todo.domain.User;

public class AddUserService {
    private UserTaskRepository database;

    public AddUserService(UserTaskRepository database) {
        this.database = database;
    }

    public void add(String userName) {
        User user = new User();
        user.setUserName(userName);
        database.addUser(user);
        database.setActiveUser(user);
    }
}
