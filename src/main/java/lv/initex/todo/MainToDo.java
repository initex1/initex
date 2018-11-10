package lv.initex.todo;

import lv.initex.todo.database.InMemoryDatabase;
import lv.initex.todo.database.UserTaskRepository;
import lv.initex.todo.services.*;
import lv.initex.todo.views.*;

import java.util.Scanner;

public class MainToDo {


    public static void main(String[] args) {
        runMainMenu();
    }

    private static void runMainMenu() {
        Scanner scanner = new Scanner(System.in);

        UserTaskRepository database = new InMemoryDatabase();

        AddUserService addUserService = new AddUserService(database);
        SelectUserService selectUserService = new SelectUserService(database);
        AddTaskService addTaskService = new AddTaskService(database);
        ModifyTaskService modifyTaskService=new ModifyTaskService(database);
        DeleteTaskService deleteTaskService = new DeleteTaskService(database);
        PrintTaskListService printTaskListService = new PrintTaskListService(database);

        AddUserView addUserView = new AddUserView(addUserService);
        SelectUserView selectUserView = new SelectUserView(selectUserService);
        AddTaskView addTaskView = new AddTaskView(addTaskService);
        ModifyTaskView modifyTaskView=new ModifyTaskView(modifyTaskService);
        DeleteTaskView deleteTaskView = new DeleteTaskView(deleteTaskService);
        PrintTaskListView printTaskListView = new PrintTaskListView(printTaskListService);

        System.out.println("Hi! this is system for creating TO-Do list");
        int input = 0;


        while (input != 7) {
            printMenuOptions();
            input = getValidMenuInput(scanner);     //Getting valid selection

            switch (input) {
                case 1:
                    addUserView.execute();
                    break;
                case 2:
                    selectUserView.execute();
                    break;
                case 3:
                    addTaskView.execute();
                    break;
                case 4:
                    modifyTaskView.execute();
                    break;
                case 5:
                    deleteTaskView.execute();
                    break;
                case 6:
                    printTaskListView.execute();
                    break;
                case 7:
                    System.out.println("Terminating program");
                    break;
            }
        }
    }

    private static void printMenuOptions() {
        System.out.println("\n"+"1-Create User"
                + "\n" + "2-Select user"
                + "\n" + "3-Add task"
                + "\n" + "4-Edit task"
                + "\n" + "5-Delete task"
                + "\n" + "6-Print user task list"
                + "\n" + "7-Exit program");
    }

    private static int getValidMenuInput(Scanner scanner) {
        boolean isValidInput = false;
        int tmpInput = -1;
        while (!isValidInput) {
            if (scanner.hasNextInt()) {
                tmpInput = scanner.nextInt();
                if (tmpInput > 0 && tmpInput < 7) {
                    return tmpInput;                                //in this case returned valid selection
                } else {
                    System.out.println("Selection is out of range");
                    printMenuOptions();
                }
            } else {
                System.out.println("Give valid integer selection!");
                scanner.next();
                printMenuOptions();
            }
        }
        return tmpInput;
    }
}
