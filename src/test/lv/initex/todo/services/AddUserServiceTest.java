package lv.initex.todo.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddUserServiceTest {
    //@Mock
    private UserTaskRepositoryMock repositoryMock = new UserTaskRepositoryMock();

    //@InjectMocks
    private AddUserService service;

    @Before
    public void init() {
        repositoryMock = new UserTaskRepositoryMock();
        service = new AddUserService(repositoryMock);
    }

    @Test
    public void addUser() {
        assertFalse(repositoryMock.isUserAdded());
        service.add("userName");
        assertTrue(repositoryMock.isUserAdded());
    }

}