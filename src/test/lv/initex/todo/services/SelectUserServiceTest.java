package lv.initex.todo.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SelectUserServiceTest {


    //@Mock
    private UserTaskRepositoryMock repositoryMock = new UserTaskRepositoryMock();

    //@InjectMocks
    private SelectUserService serviceSelectUser;


    @Before
    public void init() {
        repositoryMock = new UserTaskRepositoryMock();
        serviceSelectUser = new SelectUserService(repositoryMock);
    }

    @Test
    public void selectUser() {
        boolean setActiveUser = false;
        serviceSelectUser.selectUser("user");
        setActiveUser = repositoryMock.getActiveUser().getUserName().equals("user");
        assertTrue(setActiveUser);
    }

    @Test
    public void printAllUsers() {
        assertFalse(repositoryMock.isUsersPrinted());
        serviceSelectUser.printAllUsers();
        assertTrue(repositoryMock.isUsersPrinted());
    }
}