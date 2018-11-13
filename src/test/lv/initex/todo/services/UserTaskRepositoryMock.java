package lv.initex.todo.services;

import lv.initex.todo.database.jdbc.UserTaskRepositoryImpl;
import lv.initex.todo.domain.User;

public class UserTaskRepositoryMock extends UserTaskRepositoryImpl {

private boolean isUserAdded=false;


    @Override
    public void addUser(User user) {
        isUserAdded = true;
    }

    public boolean isUserAdded() {
        return isUserAdded;
    }
}
