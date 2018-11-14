package lv.initex.todo.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddUserServiceTest {
    //@Mock
    private UserTaskRepositoryMock repositoryMock = new UserTaskRepositoryMock();

    //@InjectMocks
    private AddUserService serviceAddUser;


    @Before
    public void init() {
        repositoryMock = new UserTaskRepositoryMock();
        serviceAddUser = new AddUserService(repositoryMock);
    }

    @Test
    public void addUser() {
        assertFalse(repositoryMock.isUserAdded());
        serviceAddUser.add("userName");
        assertTrue(repositoryMock.isUserAdded());
    }

    @Test
    public void setActiveUser() {
        boolean setActiveUser = false;
        serviceAddUser.add("userName");
        setActiveUser = repositoryMock.getActiveUser().getUserName().equals("userName");
        assertTrue(setActiveUser);
    }

}