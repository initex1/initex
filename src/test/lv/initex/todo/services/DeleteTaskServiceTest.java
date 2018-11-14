package lv.initex.todo.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class DeleteTaskServiceTest {


    //@Mock
    private UserTaskRepositoryMock repositoryMock = new UserTaskRepositoryMock();

    //@InjectMocks
    private DeleteTaskService serviceDeleteTask;


    @Before
    public void init() {
        repositoryMock = new UserTaskRepositoryMock();
        serviceDeleteTask = new DeleteTaskService(repositoryMock);
    }

    @Test
    public void deleteTask() {
        assertFalse(repositoryMock.isTaskAdded());
        serviceDeleteTask.deleteTask("task");
        assertTrue(repositoryMock.isTaskDeleted());
    }

}