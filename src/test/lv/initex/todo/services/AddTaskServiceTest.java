package lv.initex.todo.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AddTaskServiceTest {

    //@Mock
    private UserTaskRepositoryMock repositoryMock = new UserTaskRepositoryMock();

    //@InjectMocks
    private AddTaskService serviceAddTask;


    @Before
    public void init() {
        repositoryMock = new UserTaskRepositoryMock();
        serviceAddTask = new AddTaskService(repositoryMock);
    }

    @Test
    public void addTask() {
        assertFalse(repositoryMock.isTaskAdded());
        serviceAddTask.addTask("task");
        assertTrue(repositoryMock.isTaskAdded());
    }


}