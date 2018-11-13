package lv.initex.todo;

import lv.initex.todo.config.SpringAppConfig;
import lv.initex.todo.views.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Scanner;

public class MainToDo {


    public static void main(String[] args) {
        runMainMenu();
    }

    private static void runMainMenu() {
        Scanner scanner = new Scanner(System.in);

        ApplicationContext context
                = new AnnotationConfigApplicationContext(SpringAppConfig.class);

        AddUserView addUserView = context.getBean(AddUserView.class);
        SelectUserView selectUserView = context.getBean(SelectUserView.class);
        AddTaskView addTaskView = context.getBean(AddTaskView.class);
        ModifyTaskView modifyTaskView=context.getBean(ModifyTaskView.class);
        DeleteTaskView deleteTaskView = context.getBean(DeleteTaskView.class);
        PrintTaskListView printTaskListView = context.getBean(PrintTaskListView.class);

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
