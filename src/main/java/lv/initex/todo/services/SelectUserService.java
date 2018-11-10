package lv.initex.todo.services;

import lv.initex.todo.database.UserTaskRepository;
import lv.initex.todo.domain.User;

import java.util.List;
import java.util.Optional;

public class SelectUserService {
    private UserTaskRepository database;

    public SelectUserService(UserTaskRepository database) {
        this.database = database;
    }

    public User selectUser(String userName) {
        Optional<User> foundUser = database.findUserByName(userName);
        if (foundUser.isPresent()) {
            User user = foundUser.get();
            database.setActiveUser(user);
            return user;
        }
        return null;
    }

    public List<User> printAllusers() {
        return database.getAllUsers();
    }
}
