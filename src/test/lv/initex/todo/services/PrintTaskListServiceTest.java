package lv.initex.todo.services;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class PrintTaskListServiceTest {


    //@Mock
    private UserTaskRepositoryMock repositoryMock = new UserTaskRepositoryMock();

    //@InjectMocks
    private PrintTaskListService servicePrintTaskList;


    @Before
    public void init() {
        repositoryMock = new UserTaskRepositoryMock();
        servicePrintTaskList = new PrintTaskListService(repositoryMock);
    }

    @Test
    public void getAllTasks() {
        assertFalse(repositoryMock.isTasksPrinted());
        servicePrintTaskList.getAllTasks();
        assertTrue(repositoryMock.isTasksPrinted());
    }


}